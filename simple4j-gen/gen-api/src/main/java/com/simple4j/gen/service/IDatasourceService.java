package com.simple4j.gen.service;

import com.simple4j.api.base.Page;
import com.simple4j.gen.request.*;
import com.simple4j.gen.response.DatasourceDetailResponse;

import java.util.List;

/**
 * 数据源配置表 服务类
 *
 * @author hyc
 * @since 2020-09-19
 */
public interface IDatasourceService {

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
