package com.simple4j.flow.advice;

/**
 * @author hyc
 */

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig {

	@Bean
	public FilterRegistrationBean<FlowVoidFilter> filterProxy() {
		FilterRegistrationBean<FlowVoidFilter> registrationBean = new FilterRegistrationBean<>();
		FlowVoidFilter flowVoidFilter = new FlowVoidFilter();
		registrationBean.setFilter(flowVoidFilter);
		List<String> urlPatterns = new ArrayList<>();
		urlPatterns.add("/*");
		registrationBean.setUrlPatterns(urlPatterns);
		return registrationBean;
	}
}
