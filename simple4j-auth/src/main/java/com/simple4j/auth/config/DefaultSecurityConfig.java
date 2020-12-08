/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.simple4j.auth.config;

import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONUtil;
import com.simple4j.autoconfigure.jwt.config.JwtAutoConfigurer;
import com.simple4j.autoconfigure.jwt.security.TokenWriter;
import com.simple4j.web.bean.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import top.dcenter.ums.security.core.oauth.config.Auth2AutoConfigurer;
import top.dcenter.ums.security.core.oauth.properties.Auth2Properties;

/**
 * @author Joe Grandja
 * @since 0.1.0
 */
@EnableWebSecurity
public class DefaultSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private Auth2AutoConfigurer auth2AutoConfigurer;
	@Autowired
	private JwtAutoConfigurer jwtAutoConfigurer;
	@Autowired
	private Auth2Properties auth2Properties;

	@Bean
	public PasswordEncoder passwordEncoder() {
        /*
            默认为 BCryptPasswordEncoder 的实现了添加随机 salt 算法，并且能从hash后的字符串中获取 salt 进行原始密码与hash后的密码的对比
            支持格式:
            {bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG
            {noop}password
            {pbkdf2}5d923b44a6d129f3ddf3e3c8d29412723dcbde72445e8ef6bf3b508fbf17fa4ed4d6b99ca763d8dc
            {scrypt}$e0801$8bWJaSu2IKSn9Z9kM+TPXfOc/9bdYSrN1oD9qfVThWEwdRTnO7re7Ei+fUZRJ68k9lTyuTeUp4of4g24hHnazw==$OAOec05+bXxvuu/1qZ6NUR+xQYvYv7BeL1QxwRpY5Pc=
            {sha256}97cde38028ad898ebc02e690819fa220e88c62e0699403e94fff291cfffaf8410849f27605abcbc0
         */
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().mvcMatchers("/login.html", "/index.html");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin()
			.loginPage("/login.html")
			.defaultSuccessUrl("/index.html")
		;
		http
			.exceptionHandling().disable()
			.authorizeRequests()
			// 放行第三方登录入口地址与第三方登录回调地址
			// @formatter:off
			.antMatchers(HttpMethod.GET,
				auth2Properties.getRedirectUrlPrefix() + "/*",
				auth2Properties.getAuthLoginUrlPrefix() + "/*").permitAll()
			// @formatter:on
			// ========= end: 使用 justAuth-spring-security-starter 必须步骤 =========
			.mvcMatchers("/.well-known/jwks.json").permitAll()
			.anyRequest().authenticated()
			.and()
			.httpBasic().disable()
			.csrf().ignoringRequestMatchers((request) -> "/introspect".equals(request.getRequestURI()))
		;
		// ========= start: 使用 justAuth-spring-security-starter 必须步骤 =========
		// 添加 Auth2AutoConfigurer 使 OAuth2(justAuth) login 生效.
		http.apply(this.jwtAutoConfigurer);
		http.apply(this.auth2AutoConfigurer);
	}

	@Bean
	public TokenWriter tokenWriter(){
		return (outputStream, token) -> IoUtil.write(outputStream, false, JSONUtil.toJsonStr(ApiResponse.ok(token)).getBytes());
	}

}
