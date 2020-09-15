package com.simple4j.user.service;

import java.util.List;

import com.simple4j.user.base.Page;
import com.simple4j.user.request.DictAddOrUpdateRequest;
import com.simple4j.user.request.DictDetailRequest;
import com.simple4j.user.request.DictListRequest;
import com.simple4j.user.request.DictPageRequest;
import com.simple4j.user.request.DictRemoveRequest;
import com.simple4j.user.response.DictDetailResponse;

/**
 * 服务类
 *
 * @author Chill
 */
public interface IDictService {

	/**
	 * 树形结构
	 *
	 * @return
	 */
	List<DictDetailResponse> tree();

	/**
	 * 获取字典表对应中文
	 *
	 * @param code    字典编号
	 * @param dictKey 字典序号
	 * @return
	 */
	String getValue(String code, Integer dictKey);

	/**
	 * 获取字典表
	 *
	 * @param code 字典编号
	 * @return
	 */
	List<DictDetailResponse> getList(String code);

	/**
	 * 新增或修改
	 *
	 * @param dictAddOrUpdateRequest
	 * @return
	 */
	boolean submit(DictAddOrUpdateRequest dictAddOrUpdateRequest);

	/**
	 * 详情
	 */
	DictDetailResponse detail(DictDetailRequest dictDetailRequest);

	/**
	 * 列表 字典表
	 */
	List<DictDetailResponse> list(DictListRequest dictListRequest);

	/**
	 * 自定义分页 字典表
	 */
	Page<DictDetailResponse> page(DictPageRequest dictPageRequest);

	/**
	 * 删除 字典表
	 */
	void remove(DictRemoveRequest dictRemoveRequest);

}
