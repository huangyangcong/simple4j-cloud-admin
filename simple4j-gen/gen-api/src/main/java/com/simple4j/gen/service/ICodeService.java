package com.simple4j.gen.service;

import com.simple4j.api.base.BusinessException;
import com.simple4j.api.base.Page;
import com.simple4j.gen.request.*;
import com.simple4j.gen.response.CodeDetailResponse;

import java.io.OutputStream;
import java.util.List;

/**
 * 代码生成表 服务类
 *
 * @author hyc
 * @since 2020-09-19
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

	/**
	 * 代码生成
	 *
	 * @param codeGenRequest
	 */
	void codeGen(OutputStream outputStream, CodeGenRequest codeGenRequest) throws BusinessException;
}
