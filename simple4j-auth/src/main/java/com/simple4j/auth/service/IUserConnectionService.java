package com.simple4j.auth.service;

import com.simple4j.auth.entity.UserConnection;
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
	List<UserConnection> queryConnectionByProviderIdAndProviderUserId(String providerId, String providerUserId);

	/**
	 * @param authUser
	 * @param providerId
	 * @param encodeState
	 * @return
	 */
	String signUp(AuthUser authUser, String providerId, String encodeState);

	/**
	 * 绑定用户
	 *
	 * @param loginId
	 * @param authUser
	 * @param providerId
	 */
	void binding(String loginId, AuthUser authUser, String providerId);

	/**
	 * 更新用户绑定信息
	 *
	 * @param authUser
	 * @param userConnection
	 */
	void updateUserConnection(AuthUser authUser, UserConnection userConnection);
}
