package com.simple4j.flow;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * Flow模块启动类
 *
 * @author hyc
 * @since 2020-10-13
 */
@SpringCloudApplication
@EnableWebSecurity
@EnableOpenApi
@MapperScan("com.simple4j.flow.mapper")
public class FlowApplication {

  public static void main(String[] args) {
    SpringApplication.run(FlowApplication.class, args);
  }
}
