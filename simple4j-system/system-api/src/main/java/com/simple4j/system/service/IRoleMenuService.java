package com.simple4j.user.service;

import java.util.List;

import com.simple4j.user.request.MenuGrantRequest;

/**
 * 服务类
 *
 * @author Chill
 */
public interface IRoleMenuService {

	/**
	 * 获取菜单别名（权限）
	 *
	 * @param roleIds
	 * @return
	 */
	List<String> getPermission(List<Long> roleIds);

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
	boolean grant(List<Long> menuIds, List<Long> roleIds);

	/**
	 * 删除角色
	 *
	 * @param roleIds
	 * @return
	 */
	void removeByRoleIds(List<Long> roleIds);

	/**
	 * 删除角色
	 *
	 * @param menuIds
	 * @return
	 */
	void removeByMenuIds(List<Long> menuIds);
}
