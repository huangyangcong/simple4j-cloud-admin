package com.simple4j.auth.service;

import com.simple4j.auth.entity.User;
import com.simple4j.auth.request.UserLoginRequest;
import com.simple4j.auth.response.UserLoginResponse;

/**
 * 服务类
 *
 * @author hyc
 */
public interface IUserService {

	/**
	 * 注册用户
	 *
	 * @param user
	 */
	void registerUser(User user);

	/**
	 * 用户登录
	 *
	 * @param userLoginRequest
	 * @return
	 */
	String login(UserLoginRequest userLoginRequest);
}
