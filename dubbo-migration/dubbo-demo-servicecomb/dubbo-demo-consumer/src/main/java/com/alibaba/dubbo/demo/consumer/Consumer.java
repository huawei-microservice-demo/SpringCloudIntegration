package com.alibaba.dubbo.demo.consumer;

import org.apache.servicecomb.foundation.common.utils.BeanUtils;
import org.apache.servicecomb.foundation.common.utils.Log4jUtils;
import org.apache.servicecomb.provider.pojo.RpcReference;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.demo.DemoService;

/**
 * Created by ken.lj on 2017/7/31.
 */
@Component
public class Consumer {

    @RpcReference(microserviceName="provider", schemaId="providerSchema")
    private static DemoService demoService;
    
    public static void main(String[] args) throws Exception {
        Log4jUtils.init();
        BeanUtils.init();
        
        String hello = demoService.sayHello("world"); // 执行远程方法

        System.out.println(hello); // 显示调用结果
    }
}
