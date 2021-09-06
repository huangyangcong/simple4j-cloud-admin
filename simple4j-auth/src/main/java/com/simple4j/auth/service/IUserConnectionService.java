package com.simple4j.auth.service;

import com.simple4j.auth.entity.AuthConnection;
import com.simple4j.auth.entity.AuthToken;
import me.zhyd.oauth.model.AuthUser;

import java.util.List;

/**
 * 服务类
 *
 * @author hyc
 */
public interface IUserConnectionService {

	/**
	 * 获取用户连接列表
	 *
	 * @param providerId     来源
	 * @param providerUserId 用户
	 * @return
	 */
	List<AuthConnection> queryConnectionByProviderIdAndProviderUserId(String providerId, String providerUserId);

	/**
	 * 解绑用户
	 *
	 * @param userId
	 * @param providerId
	 * @param providerUserId
	 */
	void unbinding(String userId, String providerId, String providerUserId);

	/**
	 * 更新用户绑定信息
	 *
	 * @param authUser
	 * @param authConnection
	 */
	void updateUserConnection(AuthUser authUser, AuthConnection authConnection);

	/**
	 * 保存用户信息
	 *
	 * @param providerId
	 * @param loginId
	 * @param authUser
	 * @param authToken
	 */
	void saveAuthConnection(String providerId, String loginId, AuthUser authUser, AuthToken authToken);
}
