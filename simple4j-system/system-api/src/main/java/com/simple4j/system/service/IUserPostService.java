package com.simple4j.system.service;


import com.simple4j.system.request.UserPostGrantRequest;

import java.util.List;

/**
 * 服务类
 *
 * @author Chill
 */
public interface IUserPostService{


	/**
	 * 获取用户岗位id
	 *
	 * @param userId
	 * @return
	 */
	List<Long> getPostIds(Long userId);

	/**
	 * 授权岗位
	 *
	 * @param userPostGrantRequest
	 */
	void grant(UserPostGrantRequest userPostGrantRequest);

	/**
	 * 授权岗位
	 */
	void grant(List<Long> userIds, List<Long> postIds);

	/**
	 * 根据postIds删除
	 *
	 * @param postIds
	 */
	void removeByPostIds(List<String> postIds);

	/**
	 * 根据userIds删除
	 * @param userIds
	 */
	void removeByUserIds(List<String> userIds);
}