package hello;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.apache.servicecomb.provider.rest.common.RestSchema;

@RestSchema(schemaId = "greeting")
@RequestMapping(path = "/")
public class GreetingController implements IGreetingController {

  private static final String template = "Hello, %s!";

  private final AtomicLong counter = new AtomicLong();

  @Value(value = "${spring.cloud.inject.fault:null}")
  private String fault;

  @Override
  @RequestMapping(value = "/greeting", method = RequestMethod.GET)
  public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
//        String fault = DynamicPropertyFactory.getInstance().getStringProperty("spring.cloud.inject.fault", null).get();
//    if (fault != null) {
//      return new Greeting(counter.incrementAndGet(),
//          String.format(template, fault));
//    }
    return new Greeting(counter.incrementAndGet(),
        String.format(template, name));
  }
}
