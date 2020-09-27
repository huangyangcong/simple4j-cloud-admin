package com.simple4j.system.service;

import com.simple4j.api.base.Page;
import com.simple4j.system.request.NavbarGrantRequest;
import com.simple4j.system.request.NavbarMenuAddOrUpdateRequest;
import com.simple4j.system.request.NavbarMenuAddRequest;
import com.simple4j.system.request.NavbarMenuDetailRequest;
import com.simple4j.system.request.NavbarMenuListRequest;
import com.simple4j.system.request.NavbarMenuPageRequest;
import com.simple4j.system.request.NavbarMenuRemoveRequest;
import com.simple4j.system.request.NavbarMenuUpdateRequest;
import com.simple4j.system.request.NavbarPermissionRequest;
import com.simple4j.system.response.NavbarMenuDetailResponse;
import com.simple4j.system.response.NavbarPermissionResponse;

import java.util.List;


/**
 * 服务类
 *
 * @author hyc
 * @since 2020-08-26
 */
public interface INavbarMenuService {

	/**
	 * 详情
	 */
	NavbarMenuDetailResponse detail(NavbarMenuDetailRequest navbarMenuDetailRequest);

	/**
	 * 列表
	 */
	List<NavbarMenuDetailResponse> list(NavbarMenuListRequest navbarMenuListRequest);

	/**
	 * 自定义分页
	 */
	Page<NavbarMenuDetailResponse> page(NavbarMenuPageRequest navbarMenuPageRequest);

	/**
	 * 新增
	 */
	boolean add(NavbarMenuAddRequest navbarMenuAddRequest);

	/**
	 * 修改
	 */
	boolean update(NavbarMenuUpdateRequest navbarMenuUpdateRequest);

	/**
	 * 新增或修改
	 */
	boolean addOrUpdate(NavbarMenuAddOrUpdateRequest navbarMenuAddOrUpdateRequest);


	/**
	 * 删除
	 */
	boolean remove(NavbarMenuRemoveRequest navbarMenuRemoveRequest);

	/**
	 * 查询顶部菜单权限
	 *
	 * @param navbarDetailRequest
	 * @return
	 */
	NavbarPermissionResponse permission(NavbarPermissionRequest navbarDetailRequest);

	/**
	 * 顶部菜单分配
	 *
	 * @param navbarGrantRequest
	 */
	void grant(NavbarGrantRequest navbarGrantRequest);

	/**
	 * 顶部菜单分配
	 *
	 * @param navbarId
	 * @param menuIds
	 */
	void grant(Long navbarId, List<Long> menuIds);
}
