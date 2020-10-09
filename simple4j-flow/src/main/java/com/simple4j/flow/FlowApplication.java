package com.simple4j.flow;


import java.util.Set;

import com.google.common.collect.Lists;
import org.flowable.ui.common.security.SecurityScope;
import org.flowable.ui.common.security.SecurityUtils;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringCloudApplication
public class FlowApplication {

	public static void main(String[] args) {
		SecurityUtils.setSecurityScopeProvider(authentication -> new SecurityScope() {
			@Override
			public String getUserId() {
				return com.simple4j.autoconfigure.jwt.security.SecurityUtils.getCurrentUserId();
			}

			@Override
			public Set<String> getGroupIds() {
				return com.simple4j.autoconfigure.jwt.security.SecurityUtils.getCurrentGroupIds();
			}

			@Override
			public String getTenantId() {
				return com.simple4j.autoconfigure.jwt.security.SecurityUtils.getCurrentTenantId();
			}

			@Override
			public boolean hasAuthority(String s) {
				return com.simple4j.autoconfigure.jwt.security.SecurityUtils.hasPermission(s);
			}
		});
		SpringApplication.run(FlowApplication.class, args);
	}
	@Bean
	public CorsConfigurationSource corsConfigurationSource(){
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Lists.newArrayList(CorsConfiguration.ALL));
		configuration.setAllowedMethods(Lists.newArrayList(CorsConfiguration.ALL));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**",configuration);
		return source;
	}
	@Configuration
	public class WebMvcConfig implements WebMvcConfigurer {

		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**")
					.allowedOrigins("*")
					.allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
					.maxAge(3600)
					.allowCredentials(true);
		}
	}
}
