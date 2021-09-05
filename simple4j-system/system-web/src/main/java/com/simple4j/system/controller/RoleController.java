package com.simple4j.system.controller;

import com.simple4j.system.request.*;
import com.simple4j.system.response.RoleDetailResponse;
import com.simple4j.system.service.IRoleMenuService;
import com.simple4j.system.service.IRoleService;
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
@RequestMapping("/role")
@Tag(name = "角色", description = "角色")
public class RoleController {

	private final IRoleService roleService;
	private final IRoleMenuService roleMenuService;

	/**
	 * 详情
	 */
	@PostMapping("/detail")
	@Operation(summary = "详情", description = "传入role")
	public ApiResponse<RoleDetailResponse> detail(
		@Valid @RequestBody RoleDetailRequest roleDetailRequest) {
		return ApiResponse.ok(roleService.detail(roleDetailRequest));
	}

	/**
	 * 列表
	 */
	@PostMapping("/list")
	@Operation(summary = "列表", description = "传入role")
	public ApiResponse<List<RoleDetailResponse>> list(
		@Valid @RequestBody RoleListRequest roleListRequest) {
		return ApiResponse.ok(roleService.list(roleListRequest));
	}

	/**
	 * 获取角色树形结构
	 */
	@PostMapping("/tree")
	@Operation(summary = "树形结构", description = "树形结构")
	public ApiResponse<List<RoleDetailResponse>> tree(String tenantId) {
		List<RoleDetailResponse> tree = roleService.tree(tenantId);
		return ApiResponse.ok(tree);
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@Operation(summary = "新增或修改", description = "传入role")
	public ApiResponse<Void> addOrUpdate(
		@Valid @RequestBody RoleAddOrUpdateRequest roleAddOrUpdateRequest) {
		roleService.addOrUpdate(roleAddOrUpdateRequest);
		return ApiResponse.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@Operation(summary = "删除", description = "传入ids")
	public ApiResponse<Void> remove(@RequestBody RoleRemoveRequest roleRemoveRequest) {
		roleService.remove(roleRemoveRequest);
		return ApiResponse.ok();
	}

	/**
	 * 设置菜单权限
	 */
	@PostMapping("/grant")
	@Operation(summary = "权限设置", description = "传入roleId集合以及menuId集合")
	public ApiResponse<Void> grant(@Valid @RequestBody MenuGrantRequest menuGrantRequest) {
		roleMenuService.grant(menuGrantRequest);
		return ApiResponse.ok();
	}
}
