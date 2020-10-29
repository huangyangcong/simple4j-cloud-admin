package com.simple4j.system.config;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

import com.simple4j.autoconfigure.jwt.dynamic.DynamicSecurityService;

import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.ExpressionBasedFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

/**
 * @author hyc
 */
@Component
public class DynamicService implements DynamicSecurityService {

	private ApplicationContext context;

	@Override
	public FilterInvocationSecurityMetadataSource loadDataSource() {
		ObjectPostProcessor<Object> objectPostProcessor = new ObjectPostProcessor<>() {
			@Override
			public <O> O postProcess(O object) {
				return null;
			}
		};
		ExpressionUrlAuthorizationConfigurer<HttpSecurity> hExpressionUrlAuthorizationConfigurer =
				new ExpressionUrlAuthorizationConfigurer<>(context);
		hExpressionUrlAuthorizationConfigurer.getRegistry().mvcMatchers(HttpMethod.DELETE, "/user/api/v1/info").authenticated();
		HttpSecurity http = new HttpSecurity(objectPostProcessor, new AuthenticationManagerBuilder(objectPostProcessor), new HashMap<>());
		try {
			hExpressionUrlAuthorizationConfigurer.configure(http);
		} catch (Exception e) {
			return new ExpressionBasedFilterInvocationSecurityMetadataSource(new LinkedHashMap<>(), new DefaultWebSecurityExpressionHandler());
		}
		return http.getSharedObject(FilterSecurityInterceptor.class).getSecurityMetadataSource();
	}

	@Override
	public Set<String> getIgnoreUrls() {
		return new HashSet<>();
	}
}
