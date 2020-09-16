package com.simple4j.user.config;

import cn.hutool.core.util.ReflectUtil;
import com.simple4j.user.util.SecurityUtils;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author hyc
 * @version 1.0.0
 */
public class ExtendRequestResponseBodyMethodProcessor extends RequestResponseBodyMethodProcessor {


	public ExtendRequestResponseBodyMethodProcessor(List<HttpMessageConverter<?>> converters) {
		super(converters);
	}

	public ExtendRequestResponseBodyMethodProcessor(List<HttpMessageConverter<?>> converters, ContentNegotiationManager manager) {
		super(converters, manager);
	}

	public ExtendRequestResponseBodyMethodProcessor(List<HttpMessageConverter<?>> converters, List<Object> requestResponseBodyAdvice) {
		super(converters, requestResponseBodyAdvice);
	}

	public ExtendRequestResponseBodyMethodProcessor(List<HttpMessageConverter<?>> converters, ContentNegotiationManager manager, List<Object> requestResponseBodyAdvice) {
		super(converters, manager, requestResponseBodyAdvice);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		Object o = super.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
		Field tenantIdField = ReflectionUtils.findField(parameter.getDeclaringClass(), "tenantId");
		if (tenantIdField != null) {
			ReflectionUtils.setField(tenantIdField, o, SecurityUtils.getTenantId());
		}
		Field userIdField = ReflectionUtils.findField(parameter.getDeclaringClass(), "userId");
		if (userIdField != null) {
			ReflectionUtils.setField(userIdField, o, SecurityUtils.getCurrentUserId());
		}
		Field usernameField = ReflectionUtils.findField(parameter.getDeclaringClass(), "username");
		if (usernameField != null) {
			ReflectionUtils.setField(usernameField, o, SecurityUtils.getCurrentUsername());
		}
		return o;
	}
}
