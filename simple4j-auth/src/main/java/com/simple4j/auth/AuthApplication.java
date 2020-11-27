package com.simple4j.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author hyc
 */
@SpringCloudApplication
@SpringBootApplication
@EnableOpenApi
@MapperScan("com.simple4j.auth.mapper")
public class AuthApplication {
	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}
}
