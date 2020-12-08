package com.simple4j.auth.service.impl;

import com.simple4j.auth.entity.User;
import com.simple4j.auth.mapper.UserMapper;
import com.simple4j.auth.request.UserLoginRequest;
import com.simple4j.auth.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
	private AuthenticationManagerBuilder authenticationManagerBuilder;

	@Override
	public void registerUser(User user) {
		userMapper.insert(user);
	}
	@Override
	public void login(UserLoginRequest userLoginRequest) {
		String captchaKey = userLoginRequest.getCaptchaKey();
		// 校验验证码
		//		captchaService.verify(captchaKey, userLoginRequest.getCaptchaCode());
		// 登录校验
		// authentication
		UsernamePasswordAuthenticationToken authenticationToken =
			new UsernamePasswordAuthenticationToken(userLoginRequest.getUsername(), userLoginRequest.getPassword());
		authenticationManagerBuilder.getObject().authenticate(authenticationToken);
	}
}
