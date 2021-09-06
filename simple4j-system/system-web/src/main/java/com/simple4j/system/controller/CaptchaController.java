package com.simple4j.system.controller;

import com.simple4j.system.response.CaptchaResponse;
import com.simple4j.system.service.ICaptchaService;
import com.simple4j.web.bean.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

@RestController
@RequestMapping("/captcha")
@Tag(name = "验证码", description = "验证码")
@RequiredArgsConstructor
public class CaptchaController {

	private final ICaptchaService iCaptchaService;

	@PostMapping("")
	@Operation(summary = "获取验证码")
	@PermitAll()
	public ApiResponse<CaptchaResponse> captcha() {
		return ApiResponse.ok(iCaptchaService.captcha());
	}
}
