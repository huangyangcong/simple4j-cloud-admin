package com.simple4j.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class Simple4jEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Simple4jEurekaApplication.class, args);
	}

}
