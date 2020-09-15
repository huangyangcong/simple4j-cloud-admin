package com.simple4j.user.controller;

import com.newdex.web.domain.R;
import com.simple4j.web.bean.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.system.request.DictAddOrUpdateRequest;
import org.springblade.system.request.DictDetailRequest;
import org.springblade.system.request.DictListRequest;
import org.springblade.system.request.DictRemoveRequest;
import org.springblade.system.response.DictDetailResponse;
import org.springblade.system.service.IDictService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springblade.common.constant.CacheNames.DICT_LIST;
import static org.springblade.common.constant.CacheNames.DICT_VALUE;

/**
 * 控制器
 *
 * @author Chill
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dict")
@Api(value = "字典", tags = "字典")
public class DictController {

	private IDictService dictService;

	/**
	 * 详情
	 */
	@PostMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入dict")
	public ApiResponse<DictDetailResponse> detail(@Valid @RequestBody DictDetailRequest dictDetailRequest) {
		return ApiResponse.ok(dictService.detail(dictDetailRequest));
	}

	/**
	 * 列表
	 */
	@PostMapping("/list")
	@ApiOperation(value = "列表", notes = "传入dict")
	public ApiResponse<List<DictDetailResponse>> list(@Valid @RequestBody DictListRequest dictListRequest) {
		return ApiResponse.ok(dictService.list(dictListRequest));
	}

	/**
	 * 获取字典树形结构
	 *
	 * @return
	 */
	@PostMapping("/tree")
	@ApiOperation(value = "树形结构", notes = "树形结构")
	public ApiResponse<List<DictDetailResponse>> tree() {
		List<DictDetailResponse> tree = dictService.tree();
		return ApiResponse.ok(tree);
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperation(value = "新增或修改", notes = "传入dict")
	public ApiResponse submit(@Valid @RequestBody DictAddOrUpdateRequest dictAddOrUpdateRequest) {
		return R.status(dictService.submit(dictAddOrUpdateRequest));
	}


	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@CacheEvict(cacheNames = {DICT_LIST, DICT_VALUE}, allEntries = true)
	@ApiOperation(value = "删除", notes = "传入ids")
	public ApiResponse remove(@Valid @RequestBody DictRemoveRequest dictRemoveRequest) {
		dictService.remove(dictRemoveRequest);
		return ApiResponse.ok();
	}

	/**
	 * 获取字典
	 *
	 * @return
	 */
	@PostMapping("/dictionary")
	@ApiOperation(value = "获取字典", notes = "获取字典")
	public ApiResponse<List<DictDetailResponse>> dictionary(String code) {
		return ApiResponse.ok(dictService.getList(code));
	}


}
