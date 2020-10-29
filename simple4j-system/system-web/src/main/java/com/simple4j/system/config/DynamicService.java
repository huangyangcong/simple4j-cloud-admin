package com.simple4j.system.config;

import com.simple4j.autoconfigure.jwt.dynamic.DynamicSecurityService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractConfigAttributeRequestMatcherRegistry;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.ExpressionBasedFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * @author hyc
 */
@Component
public class DynamicService implements DynamicSecurityService, ApplicationContextAware {

	private ApplicationContext context;

	@Override
	public FilterInvocationSecurityMetadataSource loadDataSource() {
		ExpressionUrlAuthorizationConfigurer<HttpSecurity> hExpressionUrlAuthorizationConfigurer =
				new ExpressionUrlAuthorizationConfigurer<>(context);
		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = hExpressionUrlAuthorizationConfigurer.getRegistry();
		registry.mvcMatchers(HttpMethod.DELETE, "/user/api/v1/info").authenticated();
		Method createRequestMap = ClassUtils.getMethod(ExpressionUrlAuthorizationConfigurer.ExpressionInterceptUrlRegistry.class, "createRequestMap");
		createRequestMap.setAccessible(true);
		LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap = (LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>) ReflectionUtils.invokeMethod(createRequestMap, registry);
		return new ExpressionBasedFilterInvocationSecurityMetadataSource(requestMap, new DefaultWebSecurityExpressionHandler());
	}

	@Override
	public Set<String> getIgnoreUrls() {
		return new HashSet<>();
	}

	@Override public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
	}


}
