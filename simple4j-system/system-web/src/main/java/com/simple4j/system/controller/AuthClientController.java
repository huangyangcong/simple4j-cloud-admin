package com.simple4j.system.controller;

import com.simple4j.api.base.Page;
import com.simple4j.system.request.*;
import com.simple4j.system.response.ClientDetailResponse;
import com.simple4j.system.service.IAuthClientService;
import com.simple4j.web.bean.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 应用管理控制器
 *
 * @author hyc
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
@Tag(name = "应用管理", description = "接口")
public class AuthClientController {

	private final IAuthClientService clientService;

	/**
	 * 详情
	 */
	@PostMapping("/detail")
	@Operation(summary = "详情", description = "传入client")
	public ApiResponse<ClientDetailResponse> detail(
		@Valid @RequestBody ClientDetailRequest clientDetailRequest) {
		return ApiResponse.ok(clientService.detail(clientDetailRequest));
	}

	/**
	 * 分页
	 */
	@PostMapping("/list")
	@Operation(summary = "分页", description = "传入client")
	public ApiResponse<Page<ClientDetailResponse>> list(
		@Valid @RequestBody ClientPageRequest clientPageRequest) {
		return ApiResponse.ok(clientService.page(clientPageRequest));
	}

	/**
	 * 新增
	 */
	@PostMapping("/save")
	@Operation(summary = "新增", description = "传入client")
	public ApiResponse<Void> save(@Valid @RequestBody ClientAddRequest clientAddRequest) {
		clientService.add(clientAddRequest);
		return ApiResponse.ok();
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@Operation(summary = "修改", description = "传入client")
	public ApiResponse<Void> update(@Valid @RequestBody ClientUpdateRequest clientUpdateRequest) {
		clientService.update(clientUpdateRequest);
		return ApiResponse.ok();
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@Operation(summary = "新增或修改", description = "传入client")
	public ApiResponse<Void> submit(
		@Valid @RequestBody ClientAddOrUpdateRequest clientAddOrUpdateRequest) {
		clientService.addOrUpdate(clientAddOrUpdateRequest);
		return ApiResponse.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@Operation(summary = "逻辑删除", description = "传入ids")
	public ApiResponse<Void> remove(@Valid @RequestBody ClientRemoveRequest clientRemoveRequest) {
		clientService.remove(clientRemoveRequest);
		return ApiResponse.ok();
	}
}
