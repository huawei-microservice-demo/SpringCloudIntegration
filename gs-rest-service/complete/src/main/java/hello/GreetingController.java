package hello;

import java.util.concurrent.atomic.AtomicLong;
import com.netflix.config.DynamicPropertyFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

  private static final String template = "Hello, %s!";

  private final AtomicLong counter = new AtomicLong();

//  @Value(value = "${spring.cloud.inject.fault}")
  private String fault;

  @RequestMapping("/greeting")
  public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
    System.out.println(DynamicPropertyFactory.getInstance().getStringProperty("spring.cloud.inject.fault", null).get());
    if (fault != null) {
      return new Greeting(counter.incrementAndGet(),
          String.format(template, fault));
    }
    return new Greeting(counter.incrementAndGet(),
        String.format(template, name));
  }
}
