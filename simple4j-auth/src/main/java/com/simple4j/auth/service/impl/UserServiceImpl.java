package com.simple4j.auth.service.impl;

import com.simple4j.auth.entity.User;
import com.simple4j.auth.mapper.UserMapper;
import com.simple4j.auth.request.UserLoginRequest;
import com.simple4j.auth.response.UserLoginResponse;
import com.simple4j.auth.service.ICaptchaService;
import com.simple4j.auth.service.IUserService;
import com.simple4j.autoconfigure.jwt.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author hyc
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private ICaptchaService captchaService;

	@Override
	public void registerUser(User user) {
		userMapper.insert(user);
	}

	@Override
	public String login(UserLoginRequest userLoginRequest) {
		String captchaKey = userLoginRequest.getCaptchaKey();
		// 校验验证码
		//		captchaService.verify(captchaKey, userLoginRequest.getCaptchaCode());
		// 登录校验
		String token =
			tokenService.login(userLoginRequest.getUsername(), userLoginRequest.getPassword());
		UserLoginResponse userLoginResponse = new UserLoginResponse();
		userLoginResponse.setToken(token);
		// 删除验证码
		captchaService.deleteCaptcha(captchaKey);
		return token;
	}
}
