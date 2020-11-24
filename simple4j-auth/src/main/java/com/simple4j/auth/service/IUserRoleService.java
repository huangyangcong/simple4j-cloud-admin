package com.simple4j.auth.service;

import java.util.Set;

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
	Set<String> getRoleIds(String userId);
}
