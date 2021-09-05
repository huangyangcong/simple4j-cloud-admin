package com.simple4j.system.controller;

import com.simple4j.system.request.*;
import com.simple4j.system.response.MenuDetailResponse;
import com.simple4j.system.response.MenuRoutersResponse;
import com.simple4j.system.response.RoleMenuKeyResponse;
import com.simple4j.system.service.IMenuService;
import com.simple4j.web.bean.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/menu")
@Tag(name = "菜单", description = "菜单")
public class MenuController {

	private IMenuService menuService;

	/**
	 * 详情
	 */
	@PostMapping("/detail")
	@Operation(summary = "详情", description = "传入menu")
	public ApiResponse<MenuDetailResponse> detail(
		@Valid @RequestBody MenuDetailRequest menuDetailRequest) {
		return ApiResponse.ok(menuService.detail(menuDetailRequest));
	}

	/**
	 * 列表
	 */
	@PostMapping("/list")
	@Operation(summary = "列表", description = "传入menu")
	public ApiResponse<List<MenuDetailResponse>> list(
		@Valid @RequestBody MenuListRequest menuListRequest) {
		return ApiResponse.ok(menuService.list(menuListRequest));
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@Operation(summary = "新增或修改", description = "传入menu")
	public ApiResponse<Void> addOrUpdate(
		@Valid @RequestBody MenuAddOrUpdateRequest menuAddOrUpdateRequest) {
		menuService.addOrUpdate(menuAddOrUpdateRequest);
		return ApiResponse.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@Operation(summary = "删除", description = "传入ids")
	public ApiResponse<Void> remove(@RequestBody MenuRemoveRequest menuRemoveRequest) {
		menuService.remove(menuRemoveRequest);
		return ApiResponse.ok();
	}

	/**
	 * 前端菜单数据
	 */
	@PostMapping("/routes")
	@Operation(summary = "前端菜单数据", description = "前端菜单数据")
	public ApiResponse<List<MenuDetailResponse>> routes(
		@Valid @RequestBody MenuRoutersRequest menuRoutersRequest) {
		List<MenuDetailResponse> list = menuService.routes(menuRoutersRequest);
		return ApiResponse.ok(list);
	}

	/**
	 * 前端按钮数据
	 */
	@PostMapping("/buttons")
	@Operation(summary = "前端按钮数据", description = "前端按钮数据")
	public ApiResponse<List<MenuDetailResponse>> buttons() {
		return ApiResponse.ok(menuService.buttons());
	}

	/**
	 * 获取菜单树形结构
	 */
	@PostMapping("/tree")
	@Operation(summary = "树形结构", description = "树形结构")
	public ApiResponse<List<MenuDetailResponse>> tree() {
		return ApiResponse.ok(menuService.tree());
	}

	/**
	 * 获取权限分配树形结构
	 */
	@PostMapping("/grant-tree")
	@Operation(summary = "权限分配树形结构", description = "权限分配树形结构")
	public ApiResponse<List<MenuDetailResponse>> grantTree() {
		return ApiResponse.ok(menuService.grantTree());
	}

	/**
	 * 获取权限分配树形结构
	 */
	@PostMapping("/role-tree-keys")
	@Operation(summary = "角色所分配的树", description = "角色所分配的树")
	public ApiResponse<RoleMenuKeyResponse> roleTreeKeys(
		@Valid @RequestBody RoleMenuKeyRequest roleMenuKeyRequest) {
		return ApiResponse.ok(menuService.roleTreeKeys(roleMenuKeyRequest));
	}

	/**
	 * 获取配置的角色权限
	 */
	@PostMapping("auth-routes")
	@Operation(summary = "菜单的角色权限")
	public ApiResponse<List<MenuRoutersResponse>> authRoutes() {
		return ApiResponse.ok(menuService.authRoutes());
	}

	@PostMapping("/top-menu")
	@Operation(summary = "顶部菜单")
	public ApiResponse<List<MenuRoutersResponse>> topMenu() {
		return ApiResponse.ok(menuService.authRoutes());
	}
}
