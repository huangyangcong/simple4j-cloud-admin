package com.simple4j.user.common.config;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author hyc
 * @version 1.0.0
 */
@Configuration
@Slf4j
public class CustomerConfiguration implements WebMvcConfigurer {

	@Bean
	public PasswordEncoder passwordEncoder() {
		// 密码加密方式
		return new BCryptPasswordEncoder();
	}
	@Bean
	public JavaTimeModule javaTimeModule() {
		return new JavaTimeModule();
	}

	@Bean
	public SimpleModule simpleModule() {
		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
		simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
		return simpleModule;
	}
}
