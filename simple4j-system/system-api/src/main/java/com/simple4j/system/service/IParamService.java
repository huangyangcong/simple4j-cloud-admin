package com.simple4j.system.service;

import java.util.List;

import com.simple4j.api.base.Page;
import com.simple4j.system.request.ParamAddOrUpdateRequest;
import com.simple4j.system.request.ParamAddRequest;
import com.simple4j.system.request.ParamDetailRequest;
import com.simple4j.system.request.ParamListRequest;
import com.simple4j.system.request.ParamPageRequest;
import com.simple4j.system.request.ParamRemoveRequest;
import com.simple4j.system.request.ParamUpdateRequest;
import com.simple4j.system.response.ParamDetailResponse;

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
