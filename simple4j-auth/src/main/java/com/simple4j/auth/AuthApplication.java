package com.simple4j.auth;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author hyc
 */
@SpringBootApplication
@MapperScan("com.simple4j.auth.mapper")
public class AuthApplication {
	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI(@Value("${springdoc.version:1.0.0}") String appVersion) {
		return new OpenAPI()
			.components(new Components())
			.info(new io.swagger.v3.oas.models.info.Info().title("Gateway API").version(appVersion)
				.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}
}
