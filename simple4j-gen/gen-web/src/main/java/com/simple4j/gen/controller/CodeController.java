package com.simple4j.gen.controller;

import com.simple4j.api.base.Page;
import com.simple4j.gen.request.CodeAddOrUpdateRequest;
import com.simple4j.gen.request.CodeAddRequest;
import com.simple4j.gen.request.CodeDetailRequest;
import com.simple4j.gen.request.CodeGenRequest;
import com.simple4j.gen.request.CodeListRequest;
import com.simple4j.gen.request.CodePageRequest;
import com.simple4j.gen.request.CodeRemoveRequest;
import com.simple4j.gen.request.CodeUpdateRequest;
import com.simple4j.gen.response.CodeDetailResponse;
import com.simple4j.gen.service.ICodeService;
import com.simple4j.web.bean.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 代码生成表 控制器
 *
 * @author hyc
 * @since 2020-09-19
 */
@Controller
@AllArgsConstructor
@RequestMapping("/gen/code")
@Api(value = "代码生成表", tags = "代码生成表接口")
public class CodeController {

	private ICodeService codeService;

	/**
	 * 详情
	 */
	@ResponseBody
	@PostMapping("/detail")
	@ApiOperation(value = "详情")
	public ApiResponse<CodeDetailResponse> detail(@Valid @RequestBody CodeDetailRequest codeDetailRequest) {
		CodeDetailResponse detail = codeService.detail(codeDetailRequest);
		return ApiResponse.ok(detail);
	}

	/**
	 * 列表 代码生成表
	 */
	@ResponseBody
	@PostMapping("/list")
	@ApiOperation(value = "列表")
	public ApiResponse<List<CodeDetailResponse>> list(@Valid @RequestBody CodeListRequest codeListRequest) {
		List<CodeDetailResponse> pages = codeService.list(codeListRequest);
		return ApiResponse.ok(pages);
	}

	/**
	 * 自定义分页 代码生成表
	 */
	@ResponseBody
	@PostMapping("/page")
	@ApiOperation(value = "分页")
	public ApiResponse<Page<CodeDetailResponse>> page(@Valid @RequestBody CodePageRequest codePageRequest) {
		Page<CodeDetailResponse> pages = codeService.page(codePageRequest);
		return ApiResponse.ok(pages);
	}

	/**
	 * 新增 代码生成表
	 */
	@ResponseBody
	@PostMapping("/add")
	@ApiOperation(value = "新增")
	public ApiResponse add(@Valid @RequestBody CodeAddRequest codeAddRequest) {
		codeService.add(codeAddRequest);
		return ApiResponse.ok();
	}

	/**
	 * 修改 代码生成表
	 */
	@ResponseBody
	@PostMapping("/update")
	@ApiOperation(value = "修改")
	public ApiResponse update(@Valid @RequestBody CodeUpdateRequest codeUpdateRequest) {
		codeService.update(codeUpdateRequest);
		return ApiResponse.ok();
	}

	/**
	 * 新增或修改 代码生成表
	 */
	@ResponseBody
	@PostMapping("/submit")
	@ApiOperation(value = "新增或修改")
	public ApiResponse addOrUpdate(@Valid @RequestBody CodeAddOrUpdateRequest codeAddOrUpdateRequest) {
		codeService.addOrUpdate(codeAddOrUpdateRequest);
		return ApiResponse.ok();
	}


	/**
	 * 删除 代码生成表
	 */
	@ResponseBody
	@PostMapping("/remove")
	@ApiOperation(value = "删除")
	public ApiResponse remove(@Valid @RequestBody CodeRemoveRequest codeRemoveRequest) {
		codeService.remove(codeRemoveRequest);
		return ApiResponse.ok();
	}

	/**
	 * 代码生成
	 */
	@PostMapping("/gen-code")
	@ApiOperation(value = "代码生成", notes = "传入ids")
	public void genCode(@Valid @RequestBody CodeGenRequest codeGenRequest, HttpServletResponse response)
		throws IOException {
		// 配置文件下载
		response.setHeader("content-type", MediaType.APPLICATION_OCTET_STREAM_VALUE);
		response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
		// 下载文件能正常显示中文
		response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder
			.encode(codeGenRequest.getProjectName() + "-" + codeGenRequest.getModuleName() + ".zip",
				StandardCharsets.UTF_8));
		try (OutputStream outputStream = response.getOutputStream()) {
			codeService.codeGen(outputStream, codeGenRequest);
		}
	}
}
