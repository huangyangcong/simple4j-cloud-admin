package com.simple4j.auth.controller;

import com.simple4j.auth.response.CaptchaResponse;
import com.simple4j.auth.service.ICaptchaService;
import com.simple4j.web.bean.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/captcha")
@Api(tags = "验证码")
@RequiredArgsConstructor
public class CaptchaController {

	private final ICaptchaService captchaService;

	@PostMapping("")
	@ApiOperation(value = "获取验证码")
	@PermitAll()
	public ApiResponse<CaptchaResponse> captcha(String username) {
		return ApiResponse.ok(captchaService.captcha(username));
	}
}
