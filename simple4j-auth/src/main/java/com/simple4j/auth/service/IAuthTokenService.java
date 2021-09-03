package com.simple4j.auth.service;

import com.simple4j.auth.entity.AuthToken;

/**
 * 服务类
 *
 * @author hyc
 */
public interface IAuthTokenService {

	/**
	 * 保存token信息
	 *
	 * @param authToken
	 */
	void saveAuthToken(AuthToken authToken);

	/**
	 * 更新token信息
	 *
	 * @param authToken
	 */
	void updateAuthToken(AuthToken authToken);
}
