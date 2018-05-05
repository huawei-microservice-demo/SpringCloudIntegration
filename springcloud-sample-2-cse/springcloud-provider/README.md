## 服务提供者接入CSE

这里我们基于原始Spring Cloud应用，演示如何接入CSE
详细文档可参考[Spring Cloud应用接入CSE](https://support.huaweicloud.com/bestpractice-cse/cse_03_0092.html)

### 启动服务
直接运行ServerMain的main函数

访问[http://localhost:7111/hello/sayhi?name=springcloud](http://localhost:7111/hello/sayhi?name=springcloud)，调用helloprovider服务/hello/sayhi接口


### 本地调试
本地运行需要在src/main/resources下的application文件中增加如下配置：

application.yml：

```yml
cse:
  credentials:
    accessKey: your access key
    secretKey: your secret key
    akskCustomCipher: default
    project: cn-north-1
```

application.properties：

```property
cse.credentials.accessKey=your access key
cse.credentials.secretKey=your secret key
cse.credentials.akskCustomCipher=default
cse.credentials.project=cn-north-1
```
其中：

* cse.credentials.accessKey: 用户华为云账户AK
* cse.credentials.secretKey: 用户华为云账户SK
* cse.credentials.akskCustomCipher: AKSK存储方式，默认default为明文存储，查看[更多](https://support.huaweicloud.com/devg-cse/cse_03_0088.html)
* cse.credentials.project：注册Region，默认为华北区cn-north-1

### 镜像构建
a. 已提供Dockerfile和start.sh，可以直接在华为云微服务云平台[创建构建Job](https://servicestage.huaweicloud.com/servicestage/?project=cn-north-1#/pipeline/createjob)和[流水线](https://servicestage.huaweicloud.com/servicestage/?project=cn-north-1#/pipeline/create?from=pipeline.list)

b. 华为云微服务平台编译时默认使用自带的maven仓库，若使用自定义的maven仓库，可参考以下两种方法： 1、请将您的settings.xml存放到该项目的代码库根目录下； 2、在该项目的pom文件中设置用户repositories配置。

