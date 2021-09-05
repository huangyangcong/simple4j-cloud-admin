package com.simple4j.system.controller;

import com.simple4j.api.base.Page;
import com.simple4j.system.request.*;
import com.simple4j.system.response.TenantDetailResponse;
import com.simple4j.system.service.ITenantService;
import com.simple4j.web.bean.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/tenant")
@Tag(name = "租户管理", description = "接口")
public class TenantController {

	private ITenantService tenantService;

	/**
	 * 详情
	 */
	@PostMapping("/detail")
	@Operation(summary = "详情", description = "传入tenant")
	public ApiResponse<TenantDetailResponse> detail(
		@Valid @RequestBody TenantDetailRequest tenantDetailRequest) {
		return ApiResponse.ok(tenantService.detail(tenantDetailRequest));
	}

	/**
	 * 分页
	 */
	@PostMapping("/list")
	@Operation(summary = "分页", description = "传入tenant")
	public ApiResponse<Page<TenantDetailResponse>> list(
		@Valid @RequestBody TenantPageRequest tenantPageRequest) {
		return ApiResponse.ok(tenantService.page(tenantPageRequest));
	}

	/**
	 * 下拉数据源
	 */
	@PostMapping("/select")
	@Operation(summary = "下拉数据源", description = "传入tenant")
	public ApiResponse<List<TenantDetailResponse>> select(
		@Valid @RequestBody TenantListRequest tenantListRequest) {
		return ApiResponse.ok(tenantService.list(tenantListRequest));
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@Operation(summary = "新增或修改", description = "传入tenant")
	public ApiResponse<Void> submit(
		@Valid @RequestBody TenantAddOrUpdateRequest tenantAddOrUpdateRequest) {
		tenantService.addOrUpdate(tenantAddOrUpdateRequest);
		return ApiResponse.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@Operation(summary = "逻辑删除", description = "传入ids")
	public ApiResponse<Void> remove(@RequestBody TenantRemoveRequest tenantRemoveRequest) {
		tenantService.remove(tenantRemoveRequest);
		return ApiResponse.ok();
	}
}
