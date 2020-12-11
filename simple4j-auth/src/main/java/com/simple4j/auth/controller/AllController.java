package com.simple4j.auth.controller;

import com.simple4j.web.bean.ApiResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AllController {
	@ApiOperation(value = "鉴权")
	@RequestMapping("/*")
	public ApiResponse<Void> verify() {
		return ApiResponse.ok();
	}
}
