package com.simple4j.system.controller;

import com.simple4j.api.base.Page;
import com.simple4j.system.request.RegionAddRequest;
import com.simple4j.system.request.RegionDetailRequest;
import com.simple4j.system.request.RegionLazyListRequest;
import com.simple4j.system.request.RegionPageRequest;
import com.simple4j.system.request.RegionRemoveRequest;
import com.simple4j.system.request.RegionUpdateRequest;
import com.simple4j.system.response.RegionDetailResponse;
import com.simple4j.system.service.IRegionService;
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
 * 行政区划表 控制器
 *
 * @author hyc
 */
@RestController
@AllArgsConstructor
@RequestMapping("/region")
@Api(value = "行政区划表", tags = "行政区划表接口")
public class RegionController {

	private final IRegionService regionService;

	/**
	 * 详情
	 */
	@PostMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入region")
	public ApiResponse<RegionDetailResponse> detail(@Valid @RequestBody RegionDetailRequest regionDetailRequest) {
		RegionDetailResponse detail = regionService.detail(regionDetailRequest);
		return ApiResponse.ok(detail);
	}

	/**
	 * 分页 行政区划表
	 */
	@PostMapping("/list")
	@ApiOperation(value = "分页", notes = "传入region")
	public ApiResponse<Page<RegionDetailResponse>> list(@Valid @RequestBody RegionPageRequest regionPageRequest) {
		return ApiResponse.ok(regionService.page(regionPageRequest));
	}

	/**
	 * 懒加载列表
	 */
	@PostMapping("/lazy-list")
	@ApiOperation(value = "懒加载列表")
	public ApiResponse<List<RegionDetailResponse>> lazyList(@Valid @RequestBody RegionLazyListRequest regionLazyListRequest) {
		List<RegionDetailResponse> list = regionService.lazyList(regionLazyListRequest);
		return ApiResponse.ok(list);
	}

	/**
	 * 新增 行政区划表
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增")
	public ApiResponse save(@Valid @RequestBody RegionAddRequest regionAddRequest) {
		regionService.add(regionAddRequest);
		return ApiResponse.ok();
	}

	/**
	 * 修改 行政区划表
	 */
	@PostMapping("/update")
	@ApiOperation(value = "修改", notes = "传入region")
	public ApiResponse update(@Valid @RequestBody RegionUpdateRequest regionUpdateRequest) {
		regionService.update(regionUpdateRequest);
		return ApiResponse.ok();

	}

	/**
	 * 新增或修改 行政区划表
	 */
	@PostMapping("/submit")
	@ApiOperation(value = "新增或修改", notes = "传入region")
	public ApiResponse submit(@Valid @RequestBody RegionAddRequest regionAddRequest) {
		regionService.submit(regionAddRequest);
		return ApiResponse.ok();
	}


	/**
	 * 删除 行政区划表
	 */
	@PostMapping("/remove")
	@ApiOperation(value = "删除", notes = "传入主键")
	public ApiResponse remove(@Valid @RequestBody RegionRemoveRequest regionRemoveRequest) {
		regionService.removeRegion(regionRemoveRequest);
		return ApiResponse.ok();
	}

}
