package com.simple4j.auth.service;

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

	/**
	 * 授权
	 *
	 * @param encodeState
	 * @param authUser
	 * @param providerId
	 * @param autoSignUp
	 * @return
	 */
	String authentication(AuthUser authUser, String encodeState, String providerId, boolean autoSignUp);

	/**
	 * 第三方登陆注册用户
	 *
	 * @param authUser
	 * @param username
	 * @param decodeState
	 * @return
	 */
	String registerUser(AuthUser authUser, String username, String decodeState);
}
