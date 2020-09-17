package com.simple4j.user.controller;

import com.simple4j.api.base.Page;
import com.simple4j.user.request.ParamAddOrUpdateRequest;
import com.simple4j.user.request.ParamDetailRequest;
import com.simple4j.user.request.ParamPageRequest;
import com.simple4j.user.request.ParamRemoveRequest;
import com.simple4j.user.response.ParamDetailResponse;
import com.simple4j.user.service.IParamService;
import com.simple4j.web.bean.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 控制器
 *
 * @author Chill
 */
@RestController
@AllArgsConstructor
@RequestMapping("/param")
@Api(value = "参数管理", tags = "接口")
public class ParamController {

	private IParamService paramService;

	/**
	 * 详情
	 */
	@PostMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入param")
	public ApiResponse<ParamDetailResponse> detail(@Valid @RequestBody ParamDetailRequest paramDetailRequest) {
		return ApiResponse.ok(paramService.detail(paramDetailRequest));
	}

	/**
	 * 分页
	 */
	@PostMapping("/list")
	@ApiOperation(value = "分页", notes = "传入param")
	public ApiResponse<Page<ParamDetailResponse>> list(@Valid @RequestBody ParamPageRequest paramPageRequest) {
		return ApiResponse.ok(paramService.page(paramPageRequest));
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperation(value = "新增或修改", notes = "传入param")
	public ApiResponse submit(@Valid @RequestBody ParamAddOrUpdateRequest addOrUpdateRequest) {
		paramService.addOrUpdate(addOrUpdateRequest);
		return ApiResponse.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public ApiResponse remove(@RequestBody ParamRemoveRequest paramRemoveRequest) {
		paramService.remove(paramRemoveRequest);
		return ApiResponse.ok();
	}


}
