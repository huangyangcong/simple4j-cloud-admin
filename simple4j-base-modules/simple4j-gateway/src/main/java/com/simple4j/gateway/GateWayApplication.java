package com.simple4j.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.session.data.redis.config.annotation.web.server.EnableRedisWebSession;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableRedisWebSession
@EnableOpenApi
public class GateWayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GateWayApplication.class, args);
	}
}
