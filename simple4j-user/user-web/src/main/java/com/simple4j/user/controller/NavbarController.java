package com.simple4j.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.newdex.web.domain.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.common.util.SecurityUtils;
import org.springblade.system.request.*;
import org.springblade.system.response.NavbarDetailResponse;
import org.springblade.system.response.NavbarPermissionResponse;
import org.springblade.system.service.INavbarMenuService;
import org.springblade.system.service.INavbarService;

import javax.validation.Valid;
import java.util.List;

/**
 * 控制器
 *
 * @author Blade
 * @since 2020-08-26
 */
@RestController
@AllArgsConstructor
@RequestMapping("/navbar")
@Api(value = "", tags = "接口")
public class NavbarController {

	private INavbarService navbarService;
	private INavbarMenuService navbarMenuService;


	/**
	 * 详情
	 */
	@PostMapping("/detail")
	@ApiOperation(value = "详情")
	public ApiResponse<NavbarDetailResponse> detail(@Valid @RequestBody NavbarDetailRequest navbarDetailRequest) {
		NavbarDetailResponse detail = navbarService.detail(navbarDetailRequest);
		return ApiResponse.ok(detail);
	}

	/**
	 * 列表
	 */
	@PostMapping("/list")
	@ApiOperation(value = "列表")
	public ApiResponse<List<NavbarDetailResponse>> list(@Valid @RequestBody NavbarListRequest navbarListRequest) {
		navbarListRequest.setTenantId(SecurityUtils.getTenantId());
		List<NavbarDetailResponse> pages = navbarService.list(navbarListRequest);
		return ApiResponse.ok(pages);
	}


	/**
	 * 自定义分页
	 */
	@PostMapping("/page")
	@ApiOperation(value = "分页")
	public ApiResponse<IPage<NavbarDetailResponse>> page(@Valid @RequestBody NavbarPageRequest navbarPageRequest) {
		IPage<NavbarDetailResponse> pages = navbarService.page(navbarPageRequest);
		return ApiResponse.ok(pages);
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperation(value = "新增")
	public ApiResponse add(@Valid @RequestBody NavbarAddRequest navbarAddRequest) {
		navbarAddRequest.setTenantId(SecurityUtils.getTenantId());
		navbarService.add(navbarAddRequest);
		return ApiResponse.ok();
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperation(value = "修改")
	public ApiResponse update(@Valid @RequestBody NavbarUpdateRequest navbarUpdateRequest) {
		navbarUpdateRequest.setTenantId(SecurityUtils.getTenantId());
		navbarService.update(navbarUpdateRequest);
		return ApiResponse.ok();
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperation(value = "新增或修改")
	public ApiResponse addOrUpdate(@Valid @RequestBody NavbarAddOrUpdateRequest navbarAddOrUpdateRequest) {
		navbarAddOrUpdateRequest.setTenantId(SecurityUtils.getTenantId());
		navbarService.addOrUpdate(navbarAddOrUpdateRequest);
		return ApiResponse.ok();
	}


	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperation(value = "删除")
	public ApiResponse remove(@Valid @RequestBody NavbarRemoveRequest navbarRemoveRequest) {
		navbarService.remove(navbarRemoveRequest);
		return ApiResponse.ok();
	}

	@PostMapping("/permission")
	@ApiOperation(value = "顶部菜单权限")
	public ApiResponse<NavbarPermissionResponse> permission(@Valid @RequestBody NavbarPermissionRequest navbarPermissionRequest) {
		NavbarPermissionResponse detail = navbarMenuService.permission(navbarPermissionRequest);
		return ApiResponse.ok(detail);
	}

	@PostMapping("/grant")
	@ApiOperation(value = "顶部菜单分配")
	public ApiResponse grant(@Valid @RequestBody NavbarGrantRequest navbarGrantRequest) {
		navbarMenuService.grant(navbarGrantRequest);
		return ApiResponse.ok();
	}
}
