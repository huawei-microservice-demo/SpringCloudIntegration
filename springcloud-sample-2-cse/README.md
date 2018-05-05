## SpringCloud业务代码零修改接入CSE

这里是一个原始Spring Cloud应用经过少量的配置修改，快速接入CSE的例子

快速接入指南:[Spring Cloud应用接入CSE](https://support.huaweicloud.com/bestpractice-cse/cse_03_0092.html)体验Spring Cloud应用如何经过少量的配置修改，快速接入CSE

原始Spring Cloud:[springcloud-sample](https://github.com/huawei-microservice-demo/SpringCloudIntegration/tree/master/springcloud-sample)

访问[http://localhost:7111/hello/sayhi?name=springcloud](http://localhost:7111/hello/sayhi?name=springcloud)，调用helloprovider服务/hello/sayhi接口

访问[http://localhost:7211/hello?name=springcloud](http://localhost:7211/hello?name=springcloud)，调用helloconsumer服务/hello接口(消费helloprovider)