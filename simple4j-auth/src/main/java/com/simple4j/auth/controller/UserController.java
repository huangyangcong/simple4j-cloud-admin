package com.simple4j.auth.controller;

import com.simple4j.auth.request.UserLoginRequest;
import com.simple4j.auth.response.UserLoginResponse;
import com.simple4j.auth.service.IUserService;
import com.simple4j.autoconfigure.jwt.security.TokenService;
import com.simple4j.autoconfigure.jwt.security.servlet.ServletTokenResolve;
import com.simple4j.web.bean.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@Api(tags = "用户管理")
public class UserController {
	@Autowired
	private IUserService userService;
	@Autowired
	private ServletTokenResolve servletTokenResolve;
	@Autowired
	private TokenService tokenService;

	@ApiOperation(value = "登录")
	@PostMapping("/login")
	@PermitAll()
	public ApiResponse<String> login(@RequestBody UserLoginRequest userLoginRequest) {
		return ApiResponse.ok(userService.login(userLoginRequest));
	}

	@ApiOperation(value = "校验token")
	@PostMapping("/verify")
	@PermitAll()
	public ApiResponse<Void> verify(HttpServletRequest request) {
		String token = servletTokenResolve.resolveToken(request);
		tokenService.tokenAuth(token);
		return ApiResponse.ok();
	}
}
