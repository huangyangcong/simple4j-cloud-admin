package com.simple4j.user.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.simple4j.web.bean.ApiResponse;
import com.simple4j.user.request.CodeAddRequest;
import com.simple4j.user.request.CodeUpdateRequest;
import com.simple4j.user.request.CodeAddOrUpdateRequest;
import com.simple4j.user.request.CodeDetailRequest;
import com.simple4j.user.request.CodeListRequest;
import com.simple4j.user.request.CodeRemoveRequest;
import com.simple4j.user.request.CodePageRequest;
import com.simple4j.user.response.CodeDetailResponse;
import com.simple4j.user.base.Page;

import com.simple4j.user.service.ICodeService;

/**
 * 代码生成表 控制器
 *
 * @author Blade
 * @since 2020-09-15
 */
@RestController
@AllArgsConstructor
@RequestMapping("/code")
@Api(value = "代码生成表", tags = "代码生成表接口")
public class CodeController {

private ICodeService codeService;

	/**
	 * 详情
	 */
	@PostMapping("/detail")
	@ApiOperation(value = "详情")
	public ApiResponse<CodeDetailResponse> detail(@Valid @RequestBody CodeDetailRequest codeDetailRequest){
		CodeDetailResponse detail= codeService.detail(codeDetailRequest);
		return ApiResponse.ok(detail);
	}

	/**
	 * 列表 代码生成表
	 */
	@PostMapping("/list")
	@ApiOperation(value = "列表")
	public ApiResponse<List<CodeDetailResponse>>list(@Valid @RequestBody CodeListRequest codeListRequest){
		List<CodeDetailResponse> pages= codeService.list(codeListRequest);
		return ApiResponse.ok(pages);
	}

	/**
	 * 自定义分页 代码生成表
	 */
	@PostMapping("/page")
	@ApiOperation(value = "分页")
	public ApiResponse<Page<CodeDetailResponse>>page(@Valid @RequestBody CodePageRequest codePageRequest){
		Page<CodeDetailResponse> pages= codeService.page( codePageRequest);
		return ApiResponse.ok(pages);
	}

	/**
	 * 新增 代码生成表
	 */
	@PostMapping("/add")
	@ApiOperation(value = "新增")
	public ApiResponse add(@Valid @RequestBody CodeAddRequest codeAddRequest){
		codeService.add(codeAddRequest);
		return ApiResponse.ok();
	}

	/**
	 * 修改 代码生成表
	 */
	@PostMapping("/update")
	@ApiOperation(value = "修改")
	public ApiResponse update(@Valid @RequestBody CodeUpdateRequest codeUpdateRequest){
		codeService.update(codeUpdateRequest);
		return ApiResponse.ok();
	}

	/**
	 * 新增或修改 代码生成表
	 */
	@PostMapping("/submit")
	@ApiOperation(value = "新增或修改")
	public ApiResponse addOrUpdate(@Valid @RequestBody CodeAddOrUpdateRequest codeAddOrUpdateRequest){
		codeService.addOrUpdate(codeAddOrUpdateRequest);
		return ApiResponse.ok();
	}


	/**
	 * 删除 代码生成表
	 */
	@PostMapping("/remove")
	@ApiOperation(value = "删除")
	public ApiResponse remove(@Valid @RequestBody CodeRemoveRequest codeRemoveRequest){
		codeService.remove(codeRemoveRequest);
		return ApiResponse.ok();
	}
}
