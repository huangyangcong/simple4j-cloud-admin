package com.simple4j.flow.advice;

import com.simple4j.web.bean.ApiResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import springfox.documentation.spring.web.json.Json;

@ControllerAdvice
public class FlowResponseBody implements ResponseBodyAdvice<Object> {

	@Override
	public boolean supports(
		MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return converterType != ByteArrayHttpMessageConverter.class;
	}

	@Override
	public Object beforeBodyWrite(
		Object body,
		MethodParameter returnType,
		MediaType selectedContentType,
		Class<? extends HttpMessageConverter<?>> selectedConverterType,
		ServerHttpRequest request,
		ServerHttpResponse response) {
		if (body != null && body.getClass() == Json.class) {
			return body;
		}
		return ApiResponse.ok(body);
	}
}
