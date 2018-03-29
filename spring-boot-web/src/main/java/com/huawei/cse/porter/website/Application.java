package com.huawei.cse.porter.website;

import org.apache.servicecomb.springboot.starter.provider.EnableServiceComb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;

@SpringBootApplication(exclude=DispatcherServletAutoConfiguration.class)
@EnableServiceComb
public class Application {
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
