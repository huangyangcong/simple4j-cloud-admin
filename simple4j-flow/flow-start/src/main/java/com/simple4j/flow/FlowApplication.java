package com.simple4j.flow;


import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * Flow模块启动类
 *
 * @author Blade
 * @since 2020-10-13
 */
@SpringCloudApplication
@EnableOpenApi
@MapperScan("com.simple4j.flow.mapper")
public class FlowApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlowApplication.class, args);
	}
}
