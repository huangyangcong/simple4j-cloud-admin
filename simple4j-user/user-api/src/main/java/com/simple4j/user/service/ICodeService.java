package com.simple4j.user.service;

import com.simple4j.user.base.Page;
import com.simple4j.user.request.*;
import com.simple4j.user.response.CodeDetailResponse;

import java.util.List;


/**
 * 代码生成表 服务类
 *
 * @author Blade
 * @since 2020-09-15
 */
public interface ICodeService {

	/**
	 * 详情
	 */
	CodeDetailResponse detail(CodeDetailRequest codeDetailRequest);

	/**
	 * 列表 代码生成表
	 */
	List<CodeDetailResponse> list(CodeListRequest codeListRequest);

	/**
	 * 自定义分页 代码生成表
	 */
	Page<CodeDetailResponse> page(CodePageRequest codePageRequest);

	/**
	 * 新增 代码生成表
	 */
	void add(CodeAddRequest codeAddRequest);

	/**
	 * 修改 代码生成表
	 */
	void update(CodeUpdateRequest codeUpdateRequest);

	/**
	 * 新增或修改 代码生成表
	 */
	void addOrUpdate(CodeAddOrUpdateRequest codeAddOrUpdateRequest);


	/**
	 * 删除 代码生成表
	 */
	void remove(CodeRemoveRequest codeRemoveRequest);
}
