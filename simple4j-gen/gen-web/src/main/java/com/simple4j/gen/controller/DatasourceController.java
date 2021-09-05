package com.simple4j.gen.controller;

import com.simple4j.api.base.Page;
import com.simple4j.gen.request.*;
import com.simple4j.gen.response.DatasourceDetailResponse;
import com.simple4j.gen.service.IDatasourceService;
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
 * 数据源配置表 控制器
 *
 * @author hyc
 * @since 2020-09-19
 */
@RestController
@AllArgsConstructor
@RequestMapping("/gen/datasource")
@Tag(name = "数据源配置表", description = "数据源配置表接口")
public class DatasourceController {

	private IDatasourceService datasourceService;

	/**
	 * 详情
	 */
	@PostMapping("/detail")
	@Operation(summary = "详情")
	public ApiResponse<DatasourceDetailResponse> detail(
		@Valid @RequestBody DatasourceDetailRequest datasourceDetailRequest) {
		DatasourceDetailResponse detail = datasourceService.detail(datasourceDetailRequest);
		return ApiResponse.ok(detail);
	}

	/**
	 * 列表 数据源配置表
	 */
	@PostMapping("/list")
	@Operation(summary = "列表")
	public ApiResponse<List<DatasourceDetailResponse>> list(
		@Valid @RequestBody DatasourceListRequest datasourceListRequest) {
		List<DatasourceDetailResponse> pages = datasourceService.list(datasourceListRequest);
		return ApiResponse.ok(pages);
	}

	/**
	 * 自定义分页 数据源配置表
	 */
	@PostMapping("/page")
	@Operation(summary = "分页")
	public ApiResponse<Page<DatasourceDetailResponse>> page(
		@Valid @RequestBody DatasourcePageRequest datasourcePageRequest) {
		Page<DatasourceDetailResponse> pages = datasourceService.page(datasourcePageRequest);
		return ApiResponse.ok(pages);
	}

	/**
	 * 新增 数据源配置表
	 */
	@PostMapping("/add")
	@Operation(summary = "新增")
	public ApiResponse<Void> add(@Valid @RequestBody DatasourceAddRequest datasourceAddRequest) {
		datasourceService.add(datasourceAddRequest);
		return ApiResponse.ok();
	}

	/**
	 * 修改 数据源配置表
	 */
	@PostMapping("/update")
	@Operation(summary = "修改")
	public ApiResponse<Void> update(
		@Valid @RequestBody DatasourceUpdateRequest datasourceUpdateRequest) {
		datasourceService.update(datasourceUpdateRequest);
		return ApiResponse.ok();
	}

	/**
	 * 新增或修改 数据源配置表
	 */
	@PostMapping("/submit")
	@Operation(summary = "新增或修改")
	public ApiResponse<Void> addOrUpdate(
		@Valid @RequestBody DatasourceAddOrUpdateRequest datasourceAddOrUpdateRequest) {
		datasourceService.addOrUpdate(datasourceAddOrUpdateRequest);
		return ApiResponse.ok();
	}

	/**
	 * 删除 数据源配置表
	 */
	@PostMapping("/remove")
	@Operation(summary = "删除")
	public ApiResponse<Void> remove(
		@Valid @RequestBody DatasourceRemoveRequest datasourceRemoveRequest) {
		datasourceService.remove(datasourceRemoveRequest);
		return ApiResponse.ok();
	}
}
