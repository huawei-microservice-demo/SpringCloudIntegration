## SpringCloud业务代码零修改接入CSE

这里是一个原始Spring Cloud应用经过少量的配置修改后，快速接入CSE的Demo

快速接入指南:[Spring Cloud应用接入CSE](https://support.huaweicloud.com/bestpractice-cse/cse_03_0092.html)

原始Spring Cloud:[springcloud-sample](https://github.com/huawei-microservice-demo/SpringCloudIntegration/tree/master/springcloud-sample)

说明：本地运行此Demo请参考快速接入指南，在application.yml中增加认证信息，认证信息包含AS/SK，可以从公有云帐号的[我的凭证](https://support.huaweicloud.com/usermanual-iam/zh-cn_topic_0079477318.html)获取，云上部署无需提供认证信息，部署平台将自动认证。

访问[http://localhost:7111/hello/sayhi?name=springcloud](http://localhost:7111/hello/sayhi?name=springcloud)，调用helloprovider服务/hello/sayhi接口

访问[http://localhost:7211/hello?name=springcloud](http://localhost:7211/hello?name=springcloud)，调用helloconsumer服务/hello接口(消费helloprovider)
