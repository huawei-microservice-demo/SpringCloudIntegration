package hello;

import org.apache.servicecomb.springboot.starter.discovery.ServiceCombServerList;
import org.springframework.context.annotation.Bean;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;

public class SayHelloConfiguration {
  @Bean
  public IPing ribbonPing(IClientConfig config) {
    return new PingUrl();
  }

  @Bean
  public IRule ribbonRule(IClientConfig config) {
    return new AvailabilityFilteringRule();
  }

  @Bean
  ServerList<Server> ribbonServerList(
      IClientConfig config) {
    ServiceCombServerList serverList = new ServiceCombServerList();
    serverList.initWithNiwsConfig(config);
    return serverList;
  }
}

