package com.simple4j.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import zipkin2.server.internal.EnableZipkinServer;

@SpringBootApplication
@EnableEurekaClient
@EnableZipkinServer
public class Simple4jZipkinApplication {

	public static void main(String[] args) {
		SpringApplication.run(Simple4jZipkinApplication.class, args);
	}
}
