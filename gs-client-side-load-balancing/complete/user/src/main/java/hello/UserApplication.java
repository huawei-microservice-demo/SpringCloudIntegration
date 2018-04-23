package hello;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@RibbonClient(name = "say-hello", configuration = SayHelloConfiguration.class)
@EnableDiscoveryClient
@EnableZuulProxy
public class UserApplication {
  private RestTemplate nonLoadBalancedRestTemplate = new RestTemplate();

  @LoadBalanced
  @Bean
  RestTemplate restTemplate(){
    return new RestTemplate();
  }

  @Autowired
  RestTemplate restTemplate;

  @Autowired
  private DiscoveryClient discoveryClient;

  @RequestMapping("/hi")
  public String hi(@RequestParam(value="name", defaultValue="Artaban") String name) {
    List<ServiceInstance> instances =  discoveryClient.getInstances("say-hello");
    ServiceInstance instance = instances.get(0);
    StringBuilder uri = new StringBuilder();
    if(instance.isSecure()) {
      uri.append("https://");
    } else {
      uri.append("http://");
    }

    uri.append(instance.getHost());
    uri.append(":");
    uri.append(instance.getPort());

    String greeting = this.nonLoadBalancedRestTemplate.getForObject(uri + "/greeting", String.class);
    return String.format("%s, %s!", greeting, name);
  }

  @RequestMapping("/hi2")
  public String hi2(@RequestParam(value="name", defaultValue="Artaban") String name) {
    String greeting = this.restTemplate.getForObject("http://say-hello/greeting", String.class);
    return String.format("%s, %s!", greeting, name);
  }

  public static void main(String[] args) {
    SpringApplication.run(UserApplication.class, args);
  }
}

