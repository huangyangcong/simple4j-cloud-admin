package com.simple4j.system.controller;

import com.simple4j.api.base.Page;
import com.simple4j.system.request.*;
import com.simple4j.system.response.PostDetailResponse;
import com.simple4j.system.service.IPostService;
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
 * 岗位表 控制器
 *
 * @author hyc
 */
@RestController
@AllArgsConstructor
@RequestMapping("/post")
@Tag(name = "岗位表", description = "岗位表接口")
public class PostController {

	private IPostService postService;

	/**
	 * 详情
	 */
	@PostMapping("/detail")
	@Operation(summary = "详情", description = "传入post")
	public ApiResponse<PostDetailResponse> detail(
		@Valid @RequestBody PostDetailRequest postDetailRequest) {
		PostDetailResponse detail = postService.detail(postDetailRequest);
		return ApiResponse.ok(detail);
	}

	@PostMapping("/list")
	@Operation(summary = "列表")
	public ApiResponse<List<PostDetailResponse>> list(
		@Valid @RequestBody PostListRequest postListRequest) {
		return ApiResponse.ok(postService.list(postListRequest));
	}

	/**
	 * 分页 岗位表
	 */
	@PostMapping("/page")
	@Operation(summary = "分页", description = "传入post")
	public ApiResponse<Page<PostDetailResponse>> page(
		@Valid @RequestBody PostPageRequest postPageRequest) {
		return ApiResponse.ok(postService.page(postPageRequest));
	}

	/**
	 * 新增 岗位表
	 */
	@PostMapping("/save")
	@Operation(summary = "新增", description = "传入post")
	public ApiResponse<Void> add(@Valid @RequestBody PostAddRequest postAddRequest) {
		postService.add(postAddRequest);
		return ApiResponse.ok();
	}

	/**
	 * 修改 岗位表
	 */
	@PostMapping("/update")
	@Operation(summary = "修改", description = "传入post")
	public ApiResponse<Void> update(@Valid @RequestBody PostUpdateRequest postUpdateRequest) {
		postService.update(postUpdateRequest);
		return ApiResponse.ok();
	}

	/**
	 * 删除 岗位表
	 */
	@PostMapping("/remove")
	@Operation(summary = "逻辑删除", description = "传入ids")
	public ApiResponse<Void> remove(@RequestBody PostRemoveRequest postRemoveRequest) {
		postService.remove(postRemoveRequest);
		return ApiResponse.ok();
	}
}
