package com.simple4j.system.service;

import com.simple4j.api.base.Page;
import com.simple4j.system.request.*;
import com.simple4j.system.response.ParamDetailResponse;

import java.util.List;

/**
 * 服务类
 *
 * @author hyc
 */
public interface IParamService {

	/**
	 * 详情
	 */
	ParamDetailResponse detail(ParamDetailRequest paramDetailRequest);

	/**
	 * 列表 参数表
	 */
	List<ParamDetailResponse> list(ParamListRequest paramListRequest);

	/**
	 * 自定义分页 参数表
	 */
	Page<ParamDetailResponse> page(ParamPageRequest paramPageRequest);

	/**
	 * 新增 参数表
	 */
	boolean add(ParamAddRequest paramAddRequest);

	/**
	 * 修改 参数表
	 */
	boolean update(ParamUpdateRequest paramUpdateRequest);

	/**
	 * 新增或修改 参数表
	 */
	boolean addOrUpdate(ParamAddOrUpdateRequest paramAddOrUpdateRequest);

	/**
	 * 删除 参数表
	 */
	boolean remove(ParamRemoveRequest paramRemoveRequest);
}
