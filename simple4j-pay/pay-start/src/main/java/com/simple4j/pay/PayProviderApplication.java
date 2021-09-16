package com.simple4j.pay;

import com.simple4j.pay.config.PayConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author hyc
 */
@SpringBootApplication
public class PayProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayProviderApplication.class, args);
	}
}
