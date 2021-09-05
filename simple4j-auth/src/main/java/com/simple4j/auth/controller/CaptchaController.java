package com.simple4j.auth.controller;

import com.simple4j.auth.response.CaptchaResponse;
import com.simple4j.auth.service.ICaptchaService;
import com.simple4j.web.bean.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/captcha")
@Tag(name = "验证码管理", description = "验证码管理")
@RequiredArgsConstructor
public class CaptchaController {

	private final ICaptchaService captchaService;

	@PostMapping("")
	@Operation(summary = "获取验证码")
	@PermitAll()
	public ApiResponse<CaptchaResponse> captcha(String username) {
		return ApiResponse.ok(captchaService.captcha(username));
	}
}
