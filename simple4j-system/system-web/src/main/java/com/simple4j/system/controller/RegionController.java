package com.simple4j.system.controller;

import com.simple4j.api.base.Page;
import com.simple4j.system.request.*;
import com.simple4j.system.response.RegionDetailResponse;
import com.simple4j.system.service.IRegionService;
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
 * 行政区划表 控制器
 *
 * @author hyc
 */
@RestController
@AllArgsConstructor
@RequestMapping("/region")
@Tag(name = "行政区划表", description = "行政区划表接口")
public class RegionController {

	private final IRegionService regionService;

	/**
	 * 详情
	 */
	@PostMapping("/detail")
	@Operation(summary = "详情", description = "传入region")
	public ApiResponse<RegionDetailResponse> detail(
		@Valid @RequestBody RegionDetailRequest regionDetailRequest) {
		RegionDetailResponse detail = regionService.detail(regionDetailRequest);
		return ApiResponse.ok(detail);
	}

	/**
	 * 分页 行政区划表
	 */
	@PostMapping("/list")
	@Operation(summary = "分页", description = "传入region")
	public ApiResponse<Page<RegionDetailResponse>> list(
		@Valid @RequestBody RegionPageRequest regionPageRequest) {
		return ApiResponse.ok(regionService.page(regionPageRequest));
	}

	/**
	 * 懒加载列表
	 */
	@PostMapping("/lazy-list")
	@Operation(summary = "懒加载列表")
	public ApiResponse<List<RegionDetailResponse>> lazyList(
		@Valid @RequestBody RegionLazyListRequest regionLazyListRequest) {
		List<RegionDetailResponse> list = regionService.lazyList(regionLazyListRequest);
		return ApiResponse.ok(list);
	}

	/**
	 * 新增 行政区划表
	 */
	@PostMapping("/save")
	@Operation(summary = "新增")
	public ApiResponse<Void> save(@Valid @RequestBody RegionAddRequest regionAddRequest) {
		regionService.add(regionAddRequest);
		return ApiResponse.ok();
	}

	/**
	 * 修改 行政区划表
	 */
	@PostMapping("/update")
	@Operation(summary = "修改", description = "传入region")
	public ApiResponse<Void> update(@Valid @RequestBody RegionUpdateRequest regionUpdateRequest) {
		regionService.update(regionUpdateRequest);
		return ApiResponse.ok();
	}

	/**
	 * 新增或修改 行政区划表
	 */
	@PostMapping("/submit")
	@Operation(summary = "新增或修改", description = "传入region")
	public ApiResponse<Void> submit(@Valid @RequestBody RegionAddRequest regionAddRequest) {
		regionService.submit(regionAddRequest);
		return ApiResponse.ok();
	}

	/**
	 * 删除 行政区划表
	 */
	@PostMapping("/remove")
	@Operation(summary = "删除", description = "传入主键")
	public ApiResponse<Void> remove(@Valid @RequestBody RegionRemoveRequest regionRemoveRequest) {
		regionService.removeRegion(regionRemoveRequest);
		return ApiResponse.ok();
	}
}
