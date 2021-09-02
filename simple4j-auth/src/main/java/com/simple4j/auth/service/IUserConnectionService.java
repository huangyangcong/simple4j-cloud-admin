package com.simple4j.auth.service;

import com.simple4j.auth.entity.UserConnection;

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
}
