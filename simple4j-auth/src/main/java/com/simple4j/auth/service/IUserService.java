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
	 * @param authUser
	 * @param providerId
	 * @param encodeState
	 * @return
	 */
	String signUp(AuthUser authUser, String providerId, String encodeState);
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
	String registerUser(AuthUser authUser, String decodeState);

	/**
	 * 授权并绑定用户信息
	 *
	 * @param encodeState
	 * @param providerId
	 * @param autoSignUp
	 * @param authUser
	 * @return
	 */
	String authentication(String encodeState, String providerId, boolean autoSignUp, AuthUser authUser);
}
