package com.simple4j.system.service;

import com.simple4j.api.base.Page;
import com.simple4j.system.request.TenantAddOrUpdateRequest;
import com.simple4j.system.request.TenantDetailRequest;
import com.simple4j.system.request.TenantListRequest;
import com.simple4j.system.request.TenantPageRequest;
import com.simple4j.system.request.TenantRemoveRequest;
import com.simple4j.system.request.TenantUpdateRequest;
import com.simple4j.system.response.TenantDetailResponse;

import java.util.List;

/**
 * 服务类
 *
 * @author Chill
 */
public interface ITenantService{

	/**
	 * 根据租户编号获取实体
	 *
	 * @param tenantId
	 * @return
	 */
	TenantDetailResponse getByTenantId(String tenantId);

	/**
	 * 详情
	 */
	TenantDetailResponse detail(TenantDetailRequest tenantDetailRequest);

	/**
	 * 列表 租户表
	 */
	List<TenantDetailResponse> list(TenantListRequest tenantListRequest);

	/**
	 * 自定义分页 租户表
	 */
	Page<TenantDetailResponse> page(TenantPageRequest tenantPageRequest);

	/**
	 * 新增 租户表
	 */
	boolean add(TenantAddOrUpdateRequest tenantAddRequest);

	/**
	 * 修改 租户表
	 */
	boolean update(TenantUpdateRequest tenantUpdateRequest);

	/**
	 * 删除 租户表
	 */
	boolean remove(TenantRemoveRequest tenantRemoveRequest);


	/**
	 * 新增或修改 租户
	 *
	 * @param tenantAddOrUpdateRequest
	 */
	boolean addOrUpdate(TenantAddOrUpdateRequest tenantAddOrUpdateRequest);
}
