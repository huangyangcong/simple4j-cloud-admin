package com.simple4j.user.service;

import java.util.List;

import com.simple4j.user.request.*;
import com.simple4j.user.response.MenuDetailResponse;
import com.simple4j.user.response.MenuRoutersResponse;
import com.simple4j.user.response.RoleMenuKeyResponse;

/**
 * 服务类
 *
 * @author Chill
 */
public interface IMenuService{

	/**
	 * 详情
	 */
	MenuDetailResponse detail(MenuDetailRequest menuDetailRequest);

	/**
	 * 菜单树形结构
	 *
	 * @param navbarId
	 * @return
	 */
	List<MenuDetailResponse> routes(MenuRoutersRequest menuRoutersRequest);

	/**
	 * 按钮树形结构
	 *
	 * @return
	 */
	List<MenuDetailResponse> buttons();

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
	boolean remove(MenuRemoveRequest menuRemoveRequest);


	/**
	 * 新增或修改 菜单表
	 */
	boolean addOrUpdate(MenuAddOrUpdateRequest menuAddOrUpdateRequest);
}
