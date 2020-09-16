package com.simple4j.user.service;

import java.util.List;

import com.simple4j.user.base.Page;
import com.simple4j.user.request.CodeAddOrUpdateRequest;
import com.simple4j.user.request.CodeAddRequest;
import com.simple4j.user.request.CodeDetailRequest;
import com.simple4j.user.request.CodeListRequest;
import com.simple4j.user.request.CodePageRequest;
import com.simple4j.user.request.CodeRemoveRequest;
import com.simple4j.user.request.CodeUpdateRequest;
import com.simple4j.user.response.CodeDetailResponse;


/**
 * 代码生成表 服务类
 *
 * @author Blade
 * @since 2020-09-16
 */
public interface ICodeService{

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
	boolean add(CodeAddRequest codeAddRequest);

	/**
	 * 修改 代码生成表
	 */
	boolean update(CodeUpdateRequest codeUpdateRequest);

	/**
	 * 新增或修改 代码生成表
	 */
	boolean addOrUpdate(CodeAddOrUpdateRequest codeAddOrUpdateRequest);


	/**
	 * 删除 代码生成表
	 */
	boolean remove(CodeRemoveRequest codeRemoveRequest);
}
