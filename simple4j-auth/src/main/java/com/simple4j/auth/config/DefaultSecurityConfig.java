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
import com.simple4j.auth.filter.UsernamePasswordAuthenticationExtendFilter;
import com.simple4j.autoconfigure.jwt.dynamic.DynamicFilterInvocationSecurityMetadataSource;
import com.simple4j.autoconfigure.jwt.dynamic.DynamicSecurityService;
import com.simple4j.autoconfigure.jwt.dynamic.IgnoreAbstractRequestMatcherRegistry;
import com.simple4j.autoconfigure.jwt.security.DefaultTokenProvider;
import com.simple4j.autoconfigure.jwt.security.TokenProvider;
import com.simple4j.web.bean.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import top.dcenter.ums.security.core.oauth.config.Auth2AutoConfigurer;
import top.dcenter.ums.security.core.oauth.properties.Auth2Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Joe Grandja
 * @since 0.1.0
 */
@EnableWebSecurity
public class DefaultSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private Auth2AutoConfigurer auth2AutoConfigurer;
	@Autowired
	private DynamicSecurity dynamicSecurity;

	private TokenProvider tokenProvider = new DefaultTokenProvider("065cb6143bb79b0acdf73cbd22d33e44b105ddfefdb889e3645c59a3cd12d47d");

	@Bean
	public GrantedAuthorityDefaults grantedAuthorityDefaults() {
		// 去除 ROLE_ 前缀
		return new GrantedAuthorityDefaults("PERMISSION_");
	}
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
	protected void configure(HttpSecurity http) throws Exception {
		UsernamePasswordAuthenticationExtendFilter filter = new UsernamePasswordAuthenticationExtendFilter(authenticationManagerBean());
		filter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
		http
			.addFilter(filter)
			.exceptionHandling().disable()
			.httpBasic().disable()
			.csrf().disable().logout().logoutSuccessHandler(new LogoutSuccessHandler() {
			@Override
			public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
				response.setContentType("application/json;charset=UTF-8");
				IoUtil.write(response.getOutputStream(), false, JSONUtil.toJsonStr(ApiResponse.ok()).getBytes());
			}
		})
		;
		// ========= start: 使用 justAuth-spring-security-starter 必须步骤 =========
		// 添加 Auth2AutoConfigurer 使 OAuth2(justAuth) login 生效.
//		http.apply(this.jwtAutoConfigurer);
		http.apply(this.auth2AutoConfigurer);
		http.apply(this.dynamicSecurity);


	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return (request, response, authentication) -> {
			final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			// generator token
			String token = tokenProvider.createToken(userDetails.getUsername());
			response.setContentType("application/json;charset=UTF-8");
			IoUtil.write(response.getOutputStream(), false, JSONUtil.toJsonStr(ApiResponse.ok(token)).getBytes());
		};
	}

	@Configuration
	public static class DynamicSecurity extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>{
		@Autowired
		private Auth2Properties auth2Properties;
		@Override
		public void init(HttpSecurity http) throws Exception {
			// 有动态权限配置时添加动态权限校验过滤器
			// @formatter:off
			http
				.authorizeRequests()
				.mvcMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				.withObjectPostProcessor(
					new ObjectPostProcessor<FilterSecurityInterceptor>() {
						public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
							fsi.setSecurityMetadataSource(dynamicFilterInvocationSecurityMetadataSource(auth2Properties));
							return fsi;
						}
					});
			// @formatter:on
		}

		@Bean
		public DynamicFilterInvocationSecurityMetadataSource dynamicFilterInvocationSecurityMetadataSource(Auth2Properties auth2Properties){
			return new DynamicFilterInvocationSecurityMetadataSource(
				new DynamicSecurityService() {
					@Override
					public void loadDataSource(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry, DefaultWebSecurityExpressionHandler expressionHandler) {
						registry
							.mvcMatchers("/login").permitAll()
							// 放行第三方登录入口地址与第三方登录回调地址
							// @formatter:off
							.antMatchers(HttpMethod.GET,
								auth2Properties.getRedirectUrlPrefix() + "/*",
								auth2Properties.getAuthLoginUrlPrefix() + "/*").permitAll()
							// @formatter:on
							// ========= end: 使用 justAuth-spring-security-starter 必须步骤 =========

							.mvcMatchers(HttpMethod.POST, "/user/api/v1/info").permitAll()
							.mvcMatchers(HttpMethod.GET, "/login/oauth2/code/{registrationId}").permitAll()
							.mvcMatchers(HttpMethod.GET, "/aaa").hasAuthority("test")
							.mvcMatchers(HttpMethod.GET, "/client/test").permitAll()
							.mvcMatchers(HttpMethod.GET, "/user/api/v1/login2").permitAll()
							.antMatchers(HttpMethod.GET, "/login/oauth2/code/**").permitAll()
							.antMatchers(HttpMethod.GET, "/authorize/oauth2/code/**").permitAll()
							.antMatchers(HttpMethod.GET, "/authorize/oauth2/code/**").permitAll()
							.anyRequest()
							.authenticated();
					}
					@Override
					public void ignoreUrls(IgnoreAbstractRequestMatcherRegistry ignoreAbstractRequestMatcherRegistry) {
						ignoreAbstractRequestMatcherRegistry.mvcMatchers("/auth/login", "/auth/verify");
					}
				}
			);
		}
	}

}
