package com.simple4j.system.service;


import java.util.List;

import com.simple4j.system.request.UserRoleGrantRequest;

/**
 * 服务类
 *
 * @author Chill
 */
public interface IUserRoleService{

	/**
	 * 获取角色
	 *
	 * @param userId
	 * @return
	 */
	List<Long> getRoleIds(Long userId);

	/**
	 * 授权角色
	 *
	 * @param userRoleGrantRequest
	 * @return
	 */
	void grant(UserRoleGrantRequest userRoleGrantRequest);


	/**
	 * 授权角色
	 */
	void grant(List<Long> userIds, List<Long> roleIds);
}
