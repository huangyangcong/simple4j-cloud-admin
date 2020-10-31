package com.simple4j.system.controller;

import com.simple4j.api.base.Page;
import com.simple4j.system.request.NavbarAddOrUpdateRequest;
import com.simple4j.system.request.NavbarAddRequest;
import com.simple4j.system.request.NavbarDetailRequest;
import com.simple4j.system.request.NavbarGrantRequest;
import com.simple4j.system.request.NavbarListRequest;
import com.simple4j.system.request.NavbarPageRequest;
import com.simple4j.system.request.NavbarPermissionRequest;
import com.simple4j.system.request.NavbarRemoveRequest;
import com.simple4j.system.request.NavbarUpdateRequest;
import com.simple4j.system.response.NavbarDetailResponse;
import com.simple4j.system.response.NavbarPermissionResponse;
import com.simple4j.system.service.INavbarMenuService;
import com.simple4j.system.service.INavbarService;
import com.simple4j.web.bean.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
		List<NavbarDetailResponse> pages = navbarService.list(navbarListRequest);
		return ApiResponse.ok(pages);
	}


	/**
	 * 自定义分页
	 */
	@PostMapping("/page")
	@ApiOperation(value = "分页")
	public ApiResponse<Page<NavbarDetailResponse>> page(@Valid @RequestBody NavbarPageRequest navbarPageRequest) {
		return ApiResponse.ok(navbarService.page(navbarPageRequest));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperation(value = "新增")
	public ApiResponse add(@Valid @RequestBody NavbarAddRequest navbarAddRequest) {
		navbarService.add(navbarAddRequest);
		return ApiResponse.ok();
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperation(value = "修改")
	public ApiResponse update(@Valid @RequestBody NavbarUpdateRequest navbarUpdateRequest) {
		navbarService.update(navbarUpdateRequest);
		return ApiResponse.ok();
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperation(value = "新增或修改")
	public ApiResponse addOrUpdate(@Valid @RequestBody NavbarAddOrUpdateRequest navbarAddOrUpdateRequest) {
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
