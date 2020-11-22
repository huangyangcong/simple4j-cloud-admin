package com.simple4j.gen;

import org.mybatis.spring.annotation.MapperScan;
import springfox.documentation.oas.annotations.EnableOpenApi;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * Gen模块启动类
 *
 * @author hyc
 * @since 2020-09-19
 */
@SpringCloudApplication
@EnableOpenApi
@MapperScan("com.simple4j.gen.mapper")
public class GenApplication {

	public static void main(String[] args) {
		SpringApplication.run(GenApplication.class, args);
	}
}
