package com.simple4j.user.service;

import java.util.List;

import com.simple4j.user.base.Page;
import com.simple4j.user.request.*;
import com.simple4j.user.response.RoleDetailResponse;

/**
 * 服务类
 *
 * @author Chill
 */
public interface IRoleService{

	/**
	 * 自定义分页
	 *
	 * @param rolePageRequest
	 * @return
	 */
	Page<RoleDetailResponse> page(RolePageRequest rolePageRequest);

	/**
	 * 树形结构
	 *
	 * @param tenantId
	 * @return
	 */
	List<RoleDetailResponse> tree(String tenantId);

	/**
	 * 查询列表
	 *
	 * @param roleListRequest
	 * @return
	 */
	List<RoleDetailResponse> list(RoleListRequest roleListRequest);

	/**
	 * 获取角色ID
	 *
	 * @param tenantId
	 * @param roleNames
	 * @return
	 */
	List<Long> getRoleIds(String tenantId, List<String> roleNames);

	/**
	 * 获取角色名
	 *
	 * @param userId
	 * @return
	 */
	List<String> getRoleNames(Long userId);

	/**
	 * 根据用户id获取角色别名
	 *
	 * @param userId
	 * @return
	 */
	List<String> getRoleAlias(Long userId);


	/**
	 * 新增或修改 角色表
	 */
	boolean addOrUpdate(RoleAddOrUpdateRequest roleAddOrUpdateRequest);

	/**
	 * 查询角色详情
	 *
	 * @param roleDetailRequest
	 * @return
	 */
	RoleDetailResponse detail(RoleDetailRequest roleDetailRequest);

	/**
	 * 删除角色
	 *
	 * @param roleRemoveRequest
	 * @return
	 */
	boolean remove(RoleRemoveRequest roleRemoveRequest);
}
