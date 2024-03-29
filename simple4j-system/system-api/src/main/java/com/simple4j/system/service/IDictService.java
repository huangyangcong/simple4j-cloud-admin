package com.simple4j.system.service;

import com.simple4j.api.base.Page;
import com.simple4j.system.request.*;
import com.simple4j.system.response.DictDetailResponse;

import java.util.List;

/**
 * 服务类
 *
 * @author hyc
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
	boolean remove(DictRemoveRequest dictRemoveRequest);
}
