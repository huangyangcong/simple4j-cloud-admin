package com.simple4j.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * @author hyc
 * @version 1.0.0
 */
@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {

	@Bean
	public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
		return http
			.authorizeExchange()
			.pathMatchers(
				"/auth/user/login",
				"/auth/auth2/authorization/**",
				"/auth/auth2/login/**",
				"/auth/index.html",
				"/auth/login.html").permitAll()
			.anyExchange()
			.authenticated()
			// 禁用 CSRF
			.and()
			.formLogin().disable()
			.logout().disable()
			.csrf().disable()
			.httpBasic().disable()
			.headers()
			.frameOptions().disable()
			.and()
			.build();
	}

}
