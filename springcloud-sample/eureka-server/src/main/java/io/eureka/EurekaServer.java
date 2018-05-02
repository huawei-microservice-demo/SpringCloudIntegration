package io.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServer {

	public static void main(String[] args) {
//		new SpringApplicationBuilder(EurekaServer.class).web(true).run(args);
		SpringApplication.run(EurekaServer.class, args);
	}

}
