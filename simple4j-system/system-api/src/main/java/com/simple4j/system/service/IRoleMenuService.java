package com.simple4j.system.service;

import java.util.Set;

import com.simple4j.system.request.MenuGrantRequest;

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

	/**
	 * 角色授权
	 *
	 * @param menuGrantRequest
	 * @return
	 */
	boolean grant(MenuGrantRequest menuGrantRequest);

	/**
	 * 角色授权
	 *
	 * @param menuIds
	 * @param roleIds
	 * @return
	 */
	boolean grant(Set<String> menuIds, Set<String> roleIds);

	/**
	 * 删除角色
	 *
	 * @param roleIds
	 * @return
	 */
	void removeByRoleIds(Set<String> roleIds);

	/**
	 * 删除角色
	 *
	 * @param menuIds
	 * @return
	 */
	void removeByMenuIds(Set<String> menuIds);
}
