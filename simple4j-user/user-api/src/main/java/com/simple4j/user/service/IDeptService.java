package com.simple4j.user.service;

import java.util.List;

import com.simple4j.user.base.Page;
import com.simple4j.user.request.DeptAddRequest;
import com.simple4j.user.request.DeptDetailRequest;
import com.simple4j.user.request.DeptListRequest;
import com.simple4j.user.request.DeptPageRequest;
import com.simple4j.user.request.DeptRemoveRequest;
import com.simple4j.user.request.DeptUpdateRequest;
import com.simple4j.user.response.DeptDetailResponse;

/**
 * 服务类
 *
 * @author Chill
 */
public interface IDeptService {

	/**
	 * 树形结构
	 *
	 * @param tenantId
	 * @return
	 */
	List<DeptDetailResponse> tree(String tenantId);

	/**
	 * 获取部门ID
	 *
	 * @param tenantId
	 * @param deptNames
	 * @return
	 */
	List<Long> getDeptIds(String tenantId, List<String> deptNames);

	/**
	 * 获取部门名
	 *
	 * @param userId
	 * @return
	 */
	List<String> getDeptNames(Long userId);


	/**
	 * 详情
	 */
	DeptDetailResponse detail(DeptDetailRequest deptDetailRequest);

	/**
	 * 列表 部门表
	 */
	List<DeptDetailResponse> list(DeptListRequest deptListRequest);

	/**
	 * 自定义分页 部门表
	 */
	Page<DeptDetailResponse> page(DeptPageRequest deptPageRequest);

	/**
	 * 新增 部门表
	 */
	boolean add(DeptAddRequest deptAddRequest);

	/**
	 * 修改 部门表
	 */
	boolean update(DeptUpdateRequest deptUpdateRequest);

	/**
	 * 删除 部门表
	 */
	boolean remove(DeptRemoveRequest deptRemoveRequest);


}
