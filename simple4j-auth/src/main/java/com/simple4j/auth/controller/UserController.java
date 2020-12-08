package com.simple4j.auth.controller;

import com.simple4j.auth.request.UserLoginRequest;
import com.simple4j.auth.service.IUserService;
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

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@Api(tags = "用户管理")
public class UserController {
	@Autowired
	private IUserService userService;

	@ApiOperation(value = "登录")
	@PostMapping("/login")
	@PermitAll()
	public ApiResponse<Void> login(@RequestBody UserLoginRequest userLoginRequest) {
		userService.login(userLoginRequest);
		return ApiResponse.ok();
	}
}
