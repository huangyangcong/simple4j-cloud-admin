package com.simple4j.user.service;

import java.util.List;

import com.simple4j.api.base.Page;
import com.simple4j.user.request.DatasourceAddOrUpdateRequest;
import com.simple4j.user.request.DatasourceAddRequest;
import com.simple4j.user.request.DatasourceDetailRequest;
import com.simple4j.user.request.DatasourceListRequest;
import com.simple4j.user.request.DatasourcePageRequest;
import com.simple4j.user.request.DatasourceRemoveRequest;
import com.simple4j.user.request.DatasourceUpdateRequest;
import com.simple4j.user.response.DatasourceDetailResponse;


/**
 * 数据源配置表 服务类
 *
 * @author Blade
 * @since 2020-09-16
 */
public interface IDatasourceService{

	/**
	 * 详情
	 */
	DatasourceDetailResponse detail(DatasourceDetailRequest datasourceDetailRequest);

	/**
	 * 列表 数据源配置表
	 */
	List<DatasourceDetailResponse> list(DatasourceListRequest datasourceListRequest);

	/**
	 * 自定义分页 数据源配置表
	 */
	Page<DatasourceDetailResponse> page(DatasourcePageRequest datasourcePageRequest);

	/**
	 * 新增 数据源配置表
	 */
	boolean add(DatasourceAddRequest datasourceAddRequest);

	/**
	 * 修改 数据源配置表
	 */
	boolean update(DatasourceUpdateRequest datasourceUpdateRequest);

	/**
	 * 新增或修改 数据源配置表
	 */
	boolean addOrUpdate(DatasourceAddOrUpdateRequest datasourceAddOrUpdateRequest);


	/**
	 * 删除 数据源配置表
	 */
	boolean remove(DatasourceRemoveRequest datasourceRemoveRequest);
}
