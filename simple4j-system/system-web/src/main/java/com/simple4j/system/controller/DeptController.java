package com.simple4j.system.controller;

import com.simple4j.api.base.Page;
import com.simple4j.system.request.*;
import com.simple4j.system.response.DeptDetailResponse;
import com.simple4j.system.service.IDeptService;
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
@RequestMapping("/dept")
@Tag(name = "部门", description = "部门")
public class DeptController {

	private IDeptService deptService;

	/**
	 * 详情
	 */
	@PostMapping("/detail")
	@Operation(summary = "详情", description = "传入dept")
	public ApiResponse<DeptDetailResponse> detail(
		@Valid @RequestBody DeptDetailRequest deptDetailRequest) {
		return ApiResponse.ok(deptService.detail(deptDetailRequest));
	}

	/**
	 * 列表
	 */
	@PostMapping("/page")
	@Operation(summary = "列表")
	public ApiResponse<Page<DeptDetailResponse>> list(
		@Valid @RequestBody DeptPageRequest deptPageRequest) {
		return ApiResponse.ok(deptService.page(deptPageRequest));
	}

	/**
	 * 获取部门树形结构
	 *
	 * @return
	 */
	@PostMapping("/tree")
	@Operation(summary = "树形结构", description = "树形结构")
	public ApiResponse<List<DeptDetailResponse>> tree(String tenantId) {
		List<DeptDetailResponse> tree = deptService.tree(tenantId);
		return ApiResponse.ok(tree);
	}

	/**
	 * 新增 部门表
	 */
	@PostMapping("/add")
	@Operation(summary = "新增")
	public ApiResponse<Void> add(@Valid @RequestBody DeptAddRequest deptAddRequest) {
		deptService.add(deptAddRequest);
		return ApiResponse.ok();
	}

	/**
	 * 修改 部门表
	 */
	@PostMapping("/update")
	@Operation(summary = "修改")
	public ApiResponse<Void> update(@Valid @RequestBody DeptUpdateRequest deptUpdateRequest) {
		deptService.update(deptUpdateRequest);
		return ApiResponse.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@Operation(summary = "删除", description = "传入ids")
	public ApiResponse<Void> remove(@Valid @RequestBody DeptRemoveRequest deptRemoveRequest) {
		deptService.remove(deptRemoveRequest);
		return ApiResponse.ok();
	}
}
