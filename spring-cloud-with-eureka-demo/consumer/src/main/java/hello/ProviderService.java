package hello;

import java.util.List;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "provider")
public interface ProviderService {
  @GetMapping("/service-instances/{applicationName}")
  public List<ServiceInstanceImpl> serviceInstancesByApplicationName(
      @PathVariable(value = "applicationName") String applicationName);
}
