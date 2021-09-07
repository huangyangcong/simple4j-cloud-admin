package com.simple4j.auth.controller;

import com.simple4j.auth.request.UserLoginRequest;
import com.simple4j.auth.response.UserLoginResponse;
import com.simple4j.auth.service.IUserService;
import com.simple4j.web.bean.ApiResponse;
import com.xkcoding.justauth.AuthRequestFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthCallback;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Tag(name = "用户管理", description = "用户管理")
@Slf4j
public class UserController {

	private final AuthRequestFactory factory;
	private final IUserService userService;

	@Operation(summary = "登录")
	@PostMapping("/login")
	@PermitAll()
	public ApiResponse<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest) {
		return ApiResponse.ok(userService.login(userLoginRequest));
	}

	@Operation(summary = "登录")
	@GetMapping
	public List<String> list() {
		return factory.oauthList();
	}

	@Operation(summary = "第三方登陆（重定向）")
	@GetMapping("/login/{type}")
	public void authorize(@PathVariable String type, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String redirectUrl = userService.getRedirectUrl(request, type);
		response.sendRedirect(redirectUrl);
	}

	@Operation(summary = "第三方登陆（回调）")
	@GetMapping("/{type}/callback")
	public ApiResponse<Object> login(HttpServletRequest request, @PathVariable String type, AuthCallback callback) {
		final String encodeState = request.getParameter("state");
		String token = userService.login(encodeState, type, callback);
		return ApiResponse.ok(token);
	}
}
