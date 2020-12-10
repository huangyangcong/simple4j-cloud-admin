package com.simple4j.auth.service.impl;

import com.simple4j.auth.entity.User;
import com.simple4j.auth.mapper.UserMapper;
import com.simple4j.auth.request.UserLoginRequest;
import com.simple4j.auth.response.UserLoginResponse;
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

	@Override
	public void registerUser(User user) {
		userMapper.insert(user);
	}

	@Override
	public String login(UserLoginRequest userLoginRequest) {
		String token =
			tokenService.login(userLoginRequest.getUsername(), userLoginRequest.getPassword());
		UserLoginResponse userLoginResponse = new UserLoginResponse();
		userLoginResponse.setToken(token);
		return token;
	}
}
