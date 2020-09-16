package com.simple4j.user.service;

import java.util.List;

import com.simple4j.user.base.Page;
import com.simple4j.user.request.ParamAddOrUpdateRequest;
import com.simple4j.user.request.ParamAddRequest;
import com.simple4j.user.request.ParamDetailRequest;
import com.simple4j.user.request.ParamListRequest;
import com.simple4j.user.request.ParamPageRequest;
import com.simple4j.user.request.ParamRemoveRequest;
import com.simple4j.user.request.ParamUpdateRequest;
import com.simple4j.user.response.ParamDetailResponse;

/**
 * 服务类
 *
 * @author Chill
 */
public interface IParamService{

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
