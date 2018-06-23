本项目演示了dubbo应用对接华为云微服务引擎CSE的切换过程。原始的dubbo DEMO来源于[dubbo官网](https://github.com/alibaba/dubbo/tree/master/dubbo-demo)。目录dubbo-demo是原始的DEMO，dubbo-demo-servicecomb是改造后的DEMO。

基于开源版本ServiceComb的切换项目可以参考：https://github.com/seanyinx/dubbo-to-servicecomb 。

# 如何熟悉切换过程
1. 建议开发者首先从[dubbo官网](http://dubbo.io/)和[CSE官网](https://java.huaweicse.com/)初步了解两个框架的功能，下载入门指导并了解Provider和Consumer的运行过程。[ServiceComb的开放性设计](https://bbs.huaweicloud.com/blogs/1fc9427c088611e89fc57ca23e93a89f)介绍了CSE的总体设计思路，可以帮助用户做好选型评估。
2. 改造的主要内容，本质上是改变服务发布方式，开发者需要将使用dubbo发布的RPC接口，使用ServiceComb的RPC方式发布。主要的改造内容是针对服务（注：这里服务指一个进程对外提供的接口。不同的技术发布服务的方式不同。比如Tomcat是符合J2EE标准的容器，采用Servlet来发布服务。由于dubbo和ServiceComb都支持RPC，因此在服务层面的改造比其他技术之间的改造要小很多，当然具体的工作量还可能和开发者写代码的习惯和设计模式有关。），通常涉及到配置文件的修改、增加Annotation描述等，不涉及具体业务逻辑的改造。当然，不同的开发框架可能还提供了一些特定的API接口，这块是改造过程中最不可以预测的部分，也是改造过程中比较难的部分。好在这部分内容很少，对于ServiceComb，开发者多数情况下不会使用任何API接口。不使用框架API接口的好处是切换为其他框架的时候，改造工作量很小。
3. 我们建议开发者安装Beyond Compare或者其他源码比较工具。了解下面的改造过程从使用比较工具打开dubbo-demo和dubbo-demo-servicecomb开始。

# 改造过程中涉及的主要部分说明
## 修改依赖关系。将POM文件中使用dubbo的地方，改为使用CSE组件。（比较pom.xml）

开发者可以在dependencyManagement加入下面的配置，这样就可以更好的管理三方件依赖关系。子项目就不需要指定版本号了。dubbo未提供类似的依赖关系机制。
```
	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>com.huawei.paas.cse</groupId>
				<artifactId>cse-dependency</artifactId>
				<version>2.3.9</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>
```
POM文件主要的修改内容涉及到将如下dubbo依赖修改为ServiceComb的依赖：    
  * dubbo依赖
```
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>dubbo</artifactId>
            <version>2.5.7</version>
        </dependency>
```
  * CSE依赖
```
		<dependency>
			<groupId>com.huawei.paas.cse</groupId>
			<artifactId>cse-solution-service-engine</artifactId>
		</dependency>
```
ServiceComb提供了非常灵活的可插拔机制。每个组件都可以独立引用使用。为了简化依赖关系配置，这里直接引用了cse-solution-service-engine，它是一个封装好的集合，可以一键式启用CSE提供的服务管控能力。


## 改造服务发布的接口。（比较DemoServiceImpl）
 
在服务接口上使用@RpcSchema(schemaId = "providerSchema")标签，就可以将服务发布为CSE的方式，其中schemaId是本Provider唯一的契约标识，CSE会给服务自动生成契约，使用者可以通过契约的内容，使用浏览器或者CSE提供的客户端来访问。在dubbo的原始DEMO中，服务发布是通过dubbo-demo-provider.xml来定义的。dubbo的RPC接口没有契约，并且无法通过HTTP协议进行访问。加上标签后，可以删除dubbo-demo-provider.xml里面发布服务相关的内容。

## 改造客户端引用（比较Consumer.java)
 
在服务接口上使用@RpcReference(microserviceName="provider", schemaId="providerSchema")标签，就可以获取远程接口的引用，其中microserviceName就是Provider的微服务名，schemaId就是Provider发布的契约。在dubbo的原始DEMO中，远程引用是通过ubbo-demo-consumer.xml来定义的，并且通过DemoService demoService = (DemoService) context.getBean("demoService");来获取引用。


## 定义微服务信息。（增加microservice.yaml文件）

这个文件是本项目的元数据信息。定义了应用名称、微服务名称、本服务监听的IP和端口等信息。dubbo服务监听的信息也在dubbo-demo-provider.xml定义。增加这个文件后，可以删除dubbo相关信息。


## 修改Main，启动服务（比较Provider.java和Consumer.java)

dubbo和ServiceComb类似，都是基于Spring来启动的。ServiceComb提供了Log4jUtils和BeanUtils来简化Log4j的初始化和Spring的初始化。Log4jUtils.init方法，会默认合并所有的classpath*:config/log4j.properties文件；BeanUtils.init默认会加载所有的classpath*:META-INF/spring/*.bean.xml文件。dubbo加载的路径是在代码中指定的：new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/dubbo-demo-consumer.xml"})。

## 结束
经过以上的步骤，dubbo DEMO改造为ServiceComb的工作就完成了。正如我们说的，改造的主要部分在于“重新定义服务发布的方式”。 为了让改造后的项目更加简化，我们删除了dubbo不在使用的dubbo-demo-provider.xml和dubbo-demo-consumer.xml，并且增加了demo.bean.xml，该文件起到告知spring加载标签定义的Bean的作用。这样我们可以使用标签来定义Bean，是代码看起来更加简洁。运行原始的dubbo服务和改造后的服务，都会在日志里面输出大家熟悉的"Hello world"。在运行改造后的ServiceComb前，需要[下载](https://console.huaweicloud.com/cse/?region=cn-north-1#/cse/tools)一个服务中心在本地，并运行。




