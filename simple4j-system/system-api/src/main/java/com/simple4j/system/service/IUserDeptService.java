package com.simple4j.system.service;

import java.util.Set;

import com.simple4j.system.request.UserDeptGrantRequest;

/**
 * 服务类
 *
 * @author hyc
 */
public interface IUserDeptService {

	/**
	 * 获取用户部门ID
	 *
	 * @param userId
	 * @return
	 */
	Set<String> getDeptIds(String userId);

	/**
	 * 授权部门
	 *
	 * @param userDeptGrantRequest
	 */
	void grant(UserDeptGrantRequest userDeptGrantRequest);

	/**
	 * 授权部门
	 */
	void grant(Set<String> userIds, Set<String> deptIds);

	/**
	 * 根据部门编号删除
	 *
	 * @param deptIds
	 */
	void removeByDeptIds(Set<String> deptIds);

	/**
	 * 根据用户编号删除
	 *
	 * @param userIds
	 */
	void removeByUserIds(Set<String> userIds);
}
