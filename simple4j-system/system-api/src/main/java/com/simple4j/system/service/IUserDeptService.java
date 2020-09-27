package com.simple4j.system.service;


import com.simple4j.system.request.UserDeptGrantRequest;

import java.util.List;

/**
 * 服务类
 *
 * @author Chill
 */
public interface IUserDeptService {

	/**
	 * 获取用户部门ID
	 *
	 * @param userId
	 * @return
	 */
	List<Long> getDeptIds(Long userId);

	/**
	 * 授权部门
	 *
	 * @param userDeptGrantRequest
	 */
	void grant(UserDeptGrantRequest userDeptGrantRequest);


	/**
	 * 授权部门
	 */
	void grant(List<Long> userIds, List<Long> deptIds);

	/**
	 * 根据部门编号删除
	 *
	 * @param deptIds
	 */
	void removeByDeptIds(List<String> deptIds);

	/**
	 * 根据用户编号删除
	 *
	 * @param userIds
	 */
	void removeByUserIds(List<String> userIds);
}
