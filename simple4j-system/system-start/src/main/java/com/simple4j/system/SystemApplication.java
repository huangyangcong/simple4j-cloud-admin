package com.simple4j.system;

import com.simple4j.web.exception.handler.AuthExceptionHandler;
import com.simple4j.web.exception.handler.GlobalExceptionHandler;
import org.mybatis.spring.annotation.MapperScan;
import springfox.documentation.oas.annotations.EnableOpenApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author hyc
 */
@SpringCloudApplication
@SpringBootApplication(exclude = {GlobalExceptionHandler.class, AuthExceptionHandler.class})
@EnableOpenApi
@MapperScan("com.simple4j.system.mapper")
public class SystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SystemApplication.class, args);
	}
}
