package com.simple4j.auth.service;

import com.simple4j.auth.entity.User;

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
}
