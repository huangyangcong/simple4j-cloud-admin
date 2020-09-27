package com.simple4j.system;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusPropertiesCustomizer;
import com.simple4j.system.common.AutoFillMetaObjectHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author hyc
 */
@SpringCloudApplication
@EnableOpenApi
@MapperScan("com.simple4j.system.mapper")
public class SystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SystemApplication.class, args);
	}
	@Bean
	public MybatisPlusPropertiesCustomizer autoFill() {
		return config -> config.getGlobalConfig().setMetaObjectHandler(new AutoFillMetaObjectHandler());
	}
}
