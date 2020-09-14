package com.simple4j.user.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.simple4j.user.entity.Role;
import com.simple4j.user.request.RoleDetailRequest;
import com.simple4j.user.request.RoleListRequest;
import com.simple4j.user.request.RolePageRequest;
import com.simple4j.user.request.RoleRemoveRequest;
import com.simple4j.user.response.RoleDetailResponse;

/**
 * 服务类
 *
 * @author Chill
 */
public interface IRoleService extends IService<Role> {

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
	void remove(RoleRemoveRequest roleRemoveRequest);
}
