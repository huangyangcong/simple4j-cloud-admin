package com.simple4j.auth.service;

import java.util.List;

/**
 * 服务类
 *
 * @author hyc
 */
public interface IUserRoleService {

	/**
	 * 获取角色
	 *
	 * @param userId
	 * @return
	 */
	List<String> getRoleIds(String userId);

	/**
	 * 默认角色
	 *
	 * @return
	 */
	List<String> getDefaultRole();
}
