package com.simple4j.auth.service;

import com.simple4j.auth.entity.UserConnection;
import com.simple4j.auth.exceptions.RegisterUserFailureException;
import me.zhyd.oauth.model.AuthUser;
import org.springframework.lang.NonNull;

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
	 * 绑定用户
	 *
	 * @param loginId
	 * @param authUser
	 * @param providerId
	 */
	void binding(String loginId, AuthUser authUser, String providerId);

	/**
	 * 解绑用户
	 *
	 * @param userId
	 * @param providerId
	 * @param providerUserId
	 */
	void unbinding(String userId, String providerId, String providerUserId);

	/**
	 * 注册链接
	 *
	 * @param providerId
	 * @param authUser
	 * @param loginId
	 * @throws RegisterUserFailureException
	 */
	void registerConnection(String providerId, AuthUser authUser, String loginId) throws RegisterUserFailureException;

	/**
	 * 更新用户绑定信息
	 *
	 * @param authUser
	 * @param userConnection
	 */
	void updateUserConnection(AuthUser authUser, UserConnection userConnection);
}
