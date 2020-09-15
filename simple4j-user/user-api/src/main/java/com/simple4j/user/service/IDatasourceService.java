package com.simple4j.user.service;

import com.simple4j.user.base.Page;
import com.simple4j.user.request.*;
import com.simple4j.user.response.DatasourceDetailResponse;

import java.util.List;


/**
 * 数据源配置表 服务类
 *
 * @author Blade
 * @since 2020-09-15
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
	void add(DatasourceAddRequest datasourceAddRequest);

	/**
	 * 修改 数据源配置表
	 */
	void update(DatasourceUpdateRequest datasourceUpdateRequest);

	/**
	 * 新增或修改 数据源配置表
	 */
	void addOrUpdate(DatasourceAddOrUpdateRequest datasourceAddOrUpdateRequest);


	/**
	 * 删除 数据源配置表
	 */
	void remove(DatasourceRemoveRequest datasourceRemoveRequest);
}
