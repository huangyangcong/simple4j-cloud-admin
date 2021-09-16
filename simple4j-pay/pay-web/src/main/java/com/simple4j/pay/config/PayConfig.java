package com.simple4j.pay.config;

import com.ijpay.jdpay.JdPayApiConfig;
import com.ijpay.paypal.PayPalApiConfig;
import com.ijpay.unionpay.UnionPayApiConfig;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PayConfig implements InitializingBean {

	@Bean
	@ConfigurationProperties(prefix = "pay.ali")
	public AliPayApiExtendConfig aliPayApiConfig() {
		return new AliPayApiExtendConfig();
	}

	@Bean
	@ConfigurationProperties(prefix = "pay.jd")
	public JdPayApiConfig jdPayApiConfig() {
		return new JdPayApiConfig();
	}

	@Bean
	@ConfigurationProperties(prefix = "pay.paypal")
	public PayPalApiConfig payPalApiConfig() {
		return new PayPalApiConfig();
	}

	@Bean
	@ConfigurationProperties(prefix = "pay.union")
	public UnionPayApiConfig unionPayApiConfig() {
		return new UnionPayApiConfig();
	}

	@Bean
	@ConfigurationProperties(prefix = "pay.wx")
	public WxPayApiExtendConfig wxPayApiConfig() {
		return new WxPayApiExtendConfig();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		aliPayApiConfig().build();
	}
}
