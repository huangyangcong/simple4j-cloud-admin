package com.simple4j.auth.service;

import com.simple4j.auth.request.UserLoginRequest;
import com.simple4j.auth.response.UserLoginResponse;

/**
 * 服务类
 *
 * @author hyc
 */
public interface IUserService {


	/**
	 * 用户登录
	 *
	 * @param userLoginRequest
	 * @return
	 */
	UserLoginResponse login(UserLoginRequest userLoginRequest);
}
