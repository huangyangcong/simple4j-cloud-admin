package com.simple4j.system.controller;

import com.simple4j.api.base.Page;
import com.simple4j.system.request.ParamAddOrUpdateRequest;
import com.simple4j.system.request.ParamDetailRequest;
import com.simple4j.system.request.ParamPageRequest;
import com.simple4j.system.request.ParamRemoveRequest;
import com.simple4j.system.response.ParamDetailResponse;
import com.simple4j.system.service.IParamService;
import com.simple4j.web.bean.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 控制器
 *
 * @author hyc
 */
@RestController
@AllArgsConstructor
@RequestMapping("/param")
@Tag(name = "参数管理", description = "接口")
public class ParamController {

	private IParamService paramService;

	/**
	 * 详情
	 */
	@PostMapping("/detail")
	@Operation(summary = "详情", description = "传入param")
	public ApiResponse<ParamDetailResponse> detail(
		@Valid @RequestBody ParamDetailRequest paramDetailRequest) {
		return ApiResponse.ok(paramService.detail(paramDetailRequest));
	}

	/**
	 * 分页
	 */
	@PostMapping("/list")
	@Operation(summary = "分页", description = "传入param")
	public ApiResponse<Page<ParamDetailResponse>> list(
		@Valid @RequestBody ParamPageRequest paramPageRequest) {
		return ApiResponse.ok(paramService.page(paramPageRequest));
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@Operation(summary = "新增或修改", description = "传入param")
	public ApiResponse<Void> submit(
		@Valid @RequestBody ParamAddOrUpdateRequest addOrUpdateRequest) {
		paramService.addOrUpdate(addOrUpdateRequest);
		return ApiResponse.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@Operation(summary = "逻辑删除", description = "传入ids")
	public ApiResponse<Void> remove(@RequestBody ParamRemoveRequest paramRemoveRequest) {
		paramService.remove(paramRemoveRequest);
		return ApiResponse.ok();
	}
}
