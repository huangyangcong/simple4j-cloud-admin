package com.simple4j.auth.controller;

import com.simple4j.web.bean.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AllController {
	@Operation(summary = "鉴权")
	@GetMapping("/*")
	public ApiResponse<Void> verify() {
		return ApiResponse.ok();
	}
}
