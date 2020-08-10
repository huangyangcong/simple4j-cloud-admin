package com.simple4j.user;

import com.simple4j.user.model.Dept;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author hyc
 */
@SpringBootApplication
public class UserProviderApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserProviderApplication.class, args);
	}

	@GetMapping
	public void get(Dept dept) {

	}
}
