package com.simple4j.system.controller;

import com.simple4j.api.base.Page;
import com.simple4j.system.request.*;
import com.simple4j.system.response.ClientDetailResponse;
import com.simple4j.system.service.IAuthClientService;
import com.simple4j.web.bean.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "应用管理", tags = "接口")
public class AuthClientController {

	private final IAuthClientService clientService;

	/**
	 * 详情
	 */
	@PostMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入client")
	public ApiResponse<ClientDetailResponse> detail(
		@Valid @RequestBody ClientDetailRequest clientDetailRequest) {
		return ApiResponse.ok(clientService.detail(clientDetailRequest));
	}

	/**
	 * 分页
	 */
	@PostMapping("/list")
	@ApiOperation(value = "分页", notes = "传入client")
	public ApiResponse<Page<ClientDetailResponse>> list(
		@Valid @RequestBody ClientPageRequest clientPageRequest) {
		return ApiResponse.ok(clientService.page(clientPageRequest));
	}

	/**
	 * 新增
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入client")
	public ApiResponse<Void> save(@Valid @RequestBody ClientAddRequest clientAddRequest) {
		clientService.add(clientAddRequest);
		return ApiResponse.ok();
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperation(value = "修改", notes = "传入client")
	public ApiResponse<Void> update(@Valid @RequestBody ClientUpdateRequest clientUpdateRequest) {
		clientService.update(clientUpdateRequest);
		return ApiResponse.ok();
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperation(value = "新增或修改", notes = "传入client")
	public ApiResponse<Void> submit(
		@Valid @RequestBody ClientAddOrUpdateRequest clientAddOrUpdateRequest) {
		clientService.addOrUpdate(clientAddOrUpdateRequest);
		return ApiResponse.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public ApiResponse<Void> remove(@Valid @RequestBody ClientRemoveRequest clientRemoveRequest) {
		clientService.remove(clientRemoveRequest);
		return ApiResponse.ok();
	}
}
