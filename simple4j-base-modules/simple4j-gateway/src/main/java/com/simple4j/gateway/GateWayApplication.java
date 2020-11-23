package com.simple4j.gateway;

import org.mybatis.spring.annotation.MapperScan;
import springfox.documentation.oas.annotations.EnableOpenApi;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
@EnableOpenApi
@MapperScan("com.simple4j.gateway.user.mapper")
public class GateWayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GateWayApplication.class, args);
	}
}
