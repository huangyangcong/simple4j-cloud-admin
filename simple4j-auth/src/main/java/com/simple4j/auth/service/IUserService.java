package com.simple4j.auth.service;

import com.simple4j.auth.entity.User;
import com.simple4j.auth.request.UserLoginRequest;
import com.simple4j.auth.response.UserLoginResponse;
import me.zhyd.oauth.model.AuthUser;

import java.util.List;

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
	UserLoginResponse login(UserLoginRequest userLoginRequest);

	/**
	 * 生成用户名
	 *
	 * @param authUser
	 * @return
	 */
	String[] generateUsernames(AuthUser authUser);

	/**
	 * 判断用户名是否存在
	 *
	 * @param usernames
	 * @return
	 */
	List<Boolean> existedByUsernames(String[] usernames);
}
