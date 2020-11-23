package com.simple4j.gateway.user.service;

import java.util.Set;

/**
 * 服务类
 *
 * @author hyc
 */
public interface IRoleMenuService {

	/**
	 * 获取菜单别名（权限）
	 *
	 * @param roleIds
	 * @return
	 */
	Set<String> getPermission(Set<String> roleIds);
}
