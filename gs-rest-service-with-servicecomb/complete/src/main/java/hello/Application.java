package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import io.servicecomb.provider.pojo.RpcReference;
import io.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import io.servicecomb.springboot.starter.provider.EnableServiceComb;

@SpringBootApplication
@EnableAutoConfiguration
@EnableServiceComb
@Component
public class Application {

    @RpcReference(microserviceName = "spring-cloud-with-servicecomb-demo", schemaId = "greeting")
    private static IGreetingController hello;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        // call demo
        RestTemplate restTemplate = RestTemplateBuilder.create();
        Greeting result = restTemplate.getForEntity("cse://spring-cloud-with-servicecomb-demo/greeting", Greeting.class).getBody();
        System.out.println("result: " + result.getContent());

        result = hello.greeting("test for rpc");
        System.out.println("result: " + result.getContent());
    }
}
