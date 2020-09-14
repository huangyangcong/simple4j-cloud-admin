package com.simple4j.user.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.simple4j.user.entity.Menu;
import com.simple4j.user.request.MenuDetailRequest;
import com.simple4j.user.request.MenuListRequest;
import com.simple4j.user.request.MenuRemoveRequest;
import com.simple4j.user.request.RoleMenuKeyRequest;
import com.simple4j.user.response.MenuDetailResponse;
import com.simple4j.user.response.MenuRoutersResponse;
import com.simple4j.user.response.RoleMenuKeyResponse;

/**
 * 服务类
 *
 * @author Chill
 */
public interface IMenuService extends IService<Menu> {

	/**
	 * 详情
	 */
	MenuDetailResponse detail(MenuDetailRequest menuDetailRequest);

	/**
	 * 菜单树形结构
	 *
	 * @param roleIds
	 * @return
	 */
	List<MenuDetailResponse> routes(Long navbarId, List<Long> roleIds);

	/**
	 * 按钮树形结构
	 *
	 * @param roleIds
	 * @return
	 */
	List<MenuDetailResponse> buttons(List<Long> roleIds);

	/**
	 * 树形结构
	 *
	 * @return
	 */
	List<MenuDetailResponse> tree();

	/**
	 * 授权树形结构
	 *
	 * @return
	 */
	List<MenuDetailResponse> grantTree();

	/**
	 * 默认选中节点
	 *
	 * @param roleMenuKeyRequest
	 * @return
	 */
	RoleMenuKeyResponse roleTreeKeys(RoleMenuKeyRequest roleMenuKeyRequest);

	/**
	 * 获取配置的角色权限
	 *
	 * @return
	 */
	List<MenuRoutersResponse> authRoutes();

	/**
	 * 菜单列表
	 *
	 * @param menuListRequest
	 * @return
	 */
	List<MenuDetailResponse> list(MenuListRequest menuListRequest);

	/**
	 * 删除菜单
	 * @param menuRemoveRequest
	 */
	void remove(MenuRemoveRequest menuRemoveRequest);
}
