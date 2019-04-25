package hello;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class ConsumerApplication {
  @LoadBalanced
  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @LoadBalanced
  @Bean
  public AsyncRestTemplate asyncRestTemplate() {
    return new AsyncRestTemplate();
  }

  public static void main(String[] args) {
    SpringApplication.run(ConsumerApplication.class, args);
  }
}

@RestController
class ServiceInstanceRestController {
  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private AsyncRestTemplate asyncRestTemplate;

  @Autowired
  private ProviderService providerService;

  @RequestMapping("/consumer/restTemplate")
  public List<ServiceInstance> demo1() {
    List<ServiceInstance> xxx = (List<ServiceInstance>) restTemplate
        .getForEntity("http://provider/service-instances/consumer", List.class).getBody();
    return xxx;
  }

  @RequestMapping("/consumer/asyncRestTemplate")
  public List<ServiceInstance> demo2() {
    try {
      return (List<ServiceInstance>) asyncRestTemplate
          .getForEntity("http://provider/service-instances/consumer", List.class).get().getBody();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    return null;
  }

  @RequestMapping("/consumer/providerService")
  public List<ServiceInstanceImpl>  demo3() {
    return providerService.serviceInstancesByApplicationName("consumer");
  }
}
