package com.simple4j.user.controller;

import cn.hutool.core.util.StrUtil;
import com.simple4j.user.base.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.newdex.web.domain.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.common.util.SecurityUtils;
import org.springblade.system.mapstruct.DeptMapStruct;
import org.springblade.system.request.*;
import org.springblade.system.response.DeptDetailResponse;
import org.springblade.system.service.IDeptService;

import javax.validation.Valid;
import java.util.List;

/**
 * 控制器
 *
 * @author Chill
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dept")
@Api(value = "部门", tags = "部门")
public class DeptController {

	private IDeptService deptService;
	private DeptMapStruct deptMapStruct;

	/**
	 * 详情
	 */
	@PostMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入dept")
	public ApiResponse<DeptDetailResponse> detail(@Valid @RequestBody DeptDetailRequest deptDetailRequest) {
		return ApiResponse.ok(deptService.detail(deptDetailRequest));
	}

	/**
	 * 列表
	 */
	@PostMapping("/page")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "deptName", value = "部门名称", paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "fullName", value = "部门全称", paramType = "query", dataType = "string")
	})
	@ApiOperation(value = "列表")
	public ApiResponse<Page<DeptDetailResponse>> list(@Valid @RequestBody DeptPageRequest deptPageRequest) {
		return ApiResponse.ok(deptService.page(deptPageRequest));
	}

	/**
	 * 获取部门树形结构
	 *
	 * @return
	 */
	@PostMapping("/tree")
	@ApiOperation(value = "树形结构", notes = "树形结构")
	public ApiResponse<List<DeptDetailResponse>> tree(String tenantId) {
		List<DeptDetailResponse> tree = deptService.tree(
			StrUtil.nullToDefault(tenantId, SecurityUtils.getTenantId()));
		return ApiResponse.ok(tree);
	}

	/**
	 * 新增 部门表
	 */
	@PostMapping("/add")
	@ApiOperation(value = "新增")
	public ApiResponse add(@Valid @RequestBody DeptAddRequest deptAddRequest) {
		deptService.add(deptAddRequest);
		return ApiResponse.ok();
	}

	/**
	 * 修改 部门表
	 */
	@PostMapping("/update")
	@ApiOperation(value = "修改")
	public ApiResponse update(@Valid @RequestBody DeptUpdateRequest deptUpdateRequest) {
		deptService.update(deptUpdateRequest);
		return ApiResponse.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperation(value = "删除", notes = "传入ids")
	public ApiResponse remove(@Valid @RequestBody DeptRemoveRequest deptRemoveRequest) {
		deptService.remove(deptRemoveRequest);
		return ApiResponse.ok();
	}


}
