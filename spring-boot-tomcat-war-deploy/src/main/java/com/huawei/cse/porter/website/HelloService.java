package com.huawei.cse.porter.website;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestSchema(schemaId = "hello")
@RequestMapping(path = "/")
public class HelloService {
	@RequestMapping(path = "hello", method = RequestMethod.GET)
	public String sayHello(@RequestParam(name="name") String name) {
		return "Hello " + name;
	}
}
