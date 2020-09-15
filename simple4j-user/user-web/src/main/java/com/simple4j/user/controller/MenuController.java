package com.simple4j.user.controller;

import com.newdex.web.domain.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.common.util.SecurityUtils;
import org.springblade.system.entity.Menu;
import org.springblade.system.request.*;
import org.springblade.system.response.MenuDetailResponse;
import org.springblade.system.response.MenuRoutersResponse;
import org.springblade.system.response.RoleMenuKeyResponse;
import org.springblade.system.service.IMenuService;

import javax.validation.Valid;
import java.util.List;

/**
 * 控制器
 *
 * @author Chill
 */
@RestController
@AllArgsConstructor
@RequestMapping("/menu")
@Api(value = "菜单", tags = "菜单")
public class MenuController {

	private IMenuService menuService;

	/**
	 * 详情
	 */
	@PostMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入menu")
	public ApiResponse<MenuDetailResponse> detail(@Valid @RequestBody MenuDetailRequest menuDetailRequest) {
		return ApiResponse.ok(menuService.detail(menuDetailRequest));
	}

	/**
	 * 列表
	 */
	@PostMapping("/list")
	@ApiOperation(value = "列表", notes = "传入menu")
	public ApiResponse<List<MenuDetailResponse>> list(@Valid @RequestBody MenuListRequest menuListRequest) {
		return ApiResponse.ok(menuService.list(menuListRequest));
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperation(value = "新增或修改", notes = "传入menu")
	public ApiResponse submit(@Valid @RequestBody Menu menu) {
		return R.status(menuService.saveOrUpdate(menu));
	}


	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperation(value = "删除", notes = "传入ids")
	public ApiResponse remove(@RequestBody MenuRemoveRequest menuRemoveRequest) {
		return R.status(menuService.removeByIds(menuRemoveRequest.getMenuIds()));
	}

	/**
	 * 前端菜单数据
	 */
	@PostMapping("/routes")
	@ApiOperation(value = "前端菜单数据", notes = "前端菜单数据")
	public ApiResponse<List<MenuDetailResponse>> routes(@Valid @RequestBody MenuRoutersRequest menuRoutersRequest) {
		List<MenuDetailResponse> list =
			menuService.routes(menuRoutersRequest.getNavbarId(), SecurityUtils.isTenantAdmin() ? null :
				SecurityUtils.getCurrentUserDataRoles());
		return ApiResponse.ok(list);
	}

	/**
	 * 前端按钮数据
	 */
	@PostMapping("/buttons")
	@ApiOperation(value = "前端按钮数据", notes = "前端按钮数据")
	public ApiResponse<List<MenuDetailResponse>> buttons() {
		return ApiResponse.ok(menuService.buttons(SecurityUtils.getCurrentUserDataRoles()));
	}

	/**
	 * 获取菜单树形结构
	 */
	@PostMapping("/tree")
	@ApiOperation(value = "树形结构", notes = "树形结构")
	public ApiResponse<List<MenuDetailResponse>> tree() {
		return ApiResponse.ok(menuService.tree());
	}

	/**
	 * 获取权限分配树形结构
	 */
	@PostMapping("/grant-tree")
	@ApiOperation(value = "权限分配树形结构", notes = "权限分配树形结构")
	public ApiResponse<List<MenuDetailResponse>> grantTree() {
		return ApiResponse.ok(menuService.grantTree());
	}

	/**
	 * 获取权限分配树形结构
	 */
	@PostMapping("/role-tree-keys")
	@ApiOperation(value = "角色所分配的树", notes = "角色所分配的树")
	public ApiResponse<RoleMenuKeyResponse> roleTreeKeys(@Valid @RequestBody RoleMenuKeyRequest roleMenuKeyRequest) {
		return ApiResponse.ok(menuService.roleTreeKeys(roleMenuKeyRequest));
	}

	/**
	 * 获取配置的角色权限
	 */
	@PostMapping("auth-routes")
	@ApiOperation(value = "菜单的角色权限")
	public ApiResponse<List<MenuRoutersResponse>> authRoutes() {
		if (SecurityUtils.getCurrentUserId() == null || SecurityUtils.getCurrentUserId() == 0L) {
			return null;
		}
		return ApiResponse.ok(menuService.authRoutes());
	}

	@PostMapping("/top-menu")
	@ApiOperation(value = "顶部菜单")
	public ApiResponse<List<MenuRoutersResponse>> topMenu() {
		if (SecurityUtils.getCurrentUserId() == null || SecurityUtils.getCurrentUserId() == 0L) {
			return null;
		}
		return ApiResponse.ok(menuService.authRoutes());
	}
}
