package com.simple4j.system.service;

import java.util.Set;

import com.simple4j.system.request.UserPostGrantRequest;

/**
 * 服务类
 *
 * @author hyc
 */
public interface IUserPostService {

	/**
	 * 获取用户岗位id
	 *
	 * @param userId
	 * @return
	 */
	Set<String> getPostIds(String userId);

	/**
	 * 授权岗位
	 *
	 * @param userPostGrantRequest
	 */
	void grant(UserPostGrantRequest userPostGrantRequest);

	/**
	 * 授权岗位
	 */
	void grant(Set<String> userIds, Set<String> postIds);

	/**
	 * 根据postIds删除
	 *
	 * @param postIds
	 */
	void removeByPostIds(Set<String> postIds);

	/**
	 * 根据userIds删除
	 *
	 * @param userIds
	 */
	void removeByUserIds(Set<String> userIds);
}
