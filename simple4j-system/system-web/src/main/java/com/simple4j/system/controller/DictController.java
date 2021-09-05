package com.simple4j.system.controller;

import com.simple4j.system.request.DictAddOrUpdateRequest;
import com.simple4j.system.request.DictDetailRequest;
import com.simple4j.system.request.DictListRequest;
import com.simple4j.system.request.DictRemoveRequest;
import com.simple4j.system.response.DictDetailResponse;
import com.simple4j.system.service.IDictService;
import com.simple4j.web.bean.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 控制器
 *
 * @author hyc
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dict")
@Tag(name = "字典", description = "字典")
public class DictController {

	private IDictService dictService;

	/**
	 * 详情
	 */
	@PostMapping("/detail")
	@Operation(summary = "详情", description = "传入dict")
	public ApiResponse<DictDetailResponse> detail(
		@Valid @RequestBody DictDetailRequest dictDetailRequest) {
		return ApiResponse.ok(dictService.detail(dictDetailRequest));
	}

	/**
	 * 列表
	 */
	@PostMapping("/list")
	@Operation(summary = "列表", description = "传入dict")
	public ApiResponse<List<DictDetailResponse>> list(
		@Valid @RequestBody DictListRequest dictListRequest) {
		return ApiResponse.ok(dictService.list(dictListRequest));
	}

	/**
	 * 获取字典树形结构
	 *
	 * @return
	 */
	@PostMapping("/tree")
	@Operation(summary = "树形结构", description = "树形结构")
	public ApiResponse<List<DictDetailResponse>> tree() {
		List<DictDetailResponse> tree = dictService.tree();
		return ApiResponse.ok(tree);
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@Operation(summary = "新增或修改", description = "传入dict")
	public ApiResponse<Void> submit(
		@Valid @RequestBody DictAddOrUpdateRequest dictAddOrUpdateRequest) {
		dictService.submit(dictAddOrUpdateRequest);
		return ApiResponse.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@Operation(summary = "删除", description = "传入ids")
	public ApiResponse<Void> remove(@Valid @RequestBody DictRemoveRequest dictRemoveRequest) {
		dictService.remove(dictRemoveRequest);
		return ApiResponse.ok();
	}

	/**
	 * 获取字典
	 *
	 * @return
	 */
	@PostMapping("/dictionary")
	@Operation(summary = "获取字典", description = "获取字典")
	public ApiResponse<List<DictDetailResponse>> dictionary(String code) {
		return ApiResponse.ok(dictService.getList(code));
	}
}
