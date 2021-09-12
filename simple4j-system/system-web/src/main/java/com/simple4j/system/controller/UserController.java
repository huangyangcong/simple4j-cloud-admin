package com.simple4j.system.controller;

import com.simple4j.api.base.Page;
import com.simple4j.autoconfigure.jwt.security.TokenService;
import com.simple4j.system.request.*;
import com.simple4j.system.response.UserDetailResponse;
import com.simple4j.system.response.UserInfo;
import com.simple4j.system.response.UserLoginResponse;
import com.simple4j.system.service.IUserRoleService;
import com.simple4j.system.service.IUserService;
import com.simple4j.web.bean.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * 控制器
 *
 * @author hyc
 */
@RestController
@RequestMapping("/user/api/v1")
@AllArgsConstructor
@Tag(name = "用户管理", description = "用户管理")
public class UserController {

	private final IUserService userService;
	private final IUserRoleService userRoleService;

	@Operation(summary = "查看详情", description = "传入id")
	@PostMapping("/detail")
	public ApiResponse<UserDetailResponse> detail(
		@Valid @RequestBody UserDetailRequest userDetailRequest) {
		UserDetailResponse userDetailResponse = userService.detail(userDetailRequest);
		return ApiResponse.ok(userDetailResponse);
	}

	@Operation(summary = "查看详情", description = "传入id")
	@PostMapping("/info")
	public ApiResponse<UserInfo> info() {
		return ApiResponse.ok(userService.currentUserInfo());
	}

	@PostMapping("/page")
	@Operation(summary = "列表", description = "传入account和realName")
	public ApiResponse<Page<UserDetailResponse>> page(
		@Valid @RequestBody UserPageRequest userPageRequest) {
		return ApiResponse.ok(userService.page(userPageRequest));
	}

	@PostMapping("/submit")
	@Operation(summary = "新增或修改", description = "传入User")
	public ApiResponse<Void> submit(@Valid @RequestBody UserAddRequest userAddRequest) {
		userService.submit(userAddRequest);
		return ApiResponse.ok();
	}

	@PostMapping("/update")
	@Operation(summary = "修改", description = "传入User")
	public ApiResponse<Void> update(@Valid @RequestBody UserUpdateRequest userUpdateRequest) {
		userService.update(userUpdateRequest);
		return ApiResponse.ok();
	}

	@PostMapping("/remove")
	@Operation(summary = "删除", description = "传入地基和")
	public ApiResponse<Void> remove(@Valid @RequestBody UserRemoveRequest userRemoveRequest) {
		userService.remove(userRemoveRequest);
		return ApiResponse.ok();
	}

	@PostMapping("/grant")
	@Operation(summary = "权限设置", description = "传入roleId集合以及menuId集合")
	public ApiResponse<Void> grant(@Valid @RequestBody UserRoleGrantRequest userRoleGrantRequest) {
		userRoleService.grant(userRoleGrantRequest);
		return ApiResponse.ok();
	}

	@PostMapping("/reset-password")
	@Operation(summary = "初始化密码", description = "传入userId集合")
	public ApiResponse<Void> resetPassword(UserResetPasswordRequest userResetPasswordRequest) {
		userService.resetPassword(userResetPasswordRequest);
		return ApiResponse.ok();
	}

	@PostMapping("/update-password")
	@Operation(summary = "修改密码", description = "传入密码")
	public ApiResponse<Void> updatePassword(
		@Parameter(description = "旧密码", required = true) @RequestParam String oldPassword,
		@Parameter(description = "新密码", required = true) @RequestParam String newPassword,
		@Parameter(description = "新密码", required = true) @RequestParam String newPassword1) {
		userService.updatePassword(oldPassword, newPassword, newPassword1);
		return ApiResponse.ok();
	}

	/**
	 * 导入用户
	 */
	@PostMapping("import-user")
	@Operation(summary = "导入用户", description = "传入excel")
	public ApiResponse<Void> importUser(MultipartFile file, Integer isCovered) {
		String filename = file.getOriginalFilename();
		InputStream inputStream = null;
		try {
			inputStream = new BufferedInputStream(file.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		userService.importUser(inputStream, filename);
		return ApiResponse.ok();
	}

	/**
	 * 导出用户
	 */
	@SneakyThrows
	@PostMapping("export-user")
	@Operation(summary = "导出用户", description = "传入user")
	public void exportUser(@Schema(hidden = true) UserListRequest userListRequest,
						   HttpServletResponse response) {
		response.setContentType("application/vnd.ms-excel");
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
		String fileName = URLEncoder.encode("用户数据导出", StandardCharsets.UTF_8.name());
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
		userService.exportUser(response.getOutputStream(), userListRequest);
	}

	/**
	 * 导出模板
	 */
	@SneakyThrows
	@PostMapping("export-template")
	@Operation(summary = "导出模板")
	public void exportUser(HttpServletResponse response) {
		response.setContentType("application/vnd.ms-excel");
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
		String fileName = URLEncoder.encode("用户数据模板", StandardCharsets.UTF_8.name());
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
		userService.exportUser(response.getOutputStream());
	}

	/**
	 * 第三方注册用户
	 */
	@PostMapping("/register-guest")
	@Operation(summary = "第三方注册用户", description = "传入user")
	public ApiResponse<Void> registerGuest(
		@Valid @RequestBody UserRegisterGuestRequest userRegisterGuestRequest) {
		userService.registerGuest(userRegisterGuestRequest);
		return ApiResponse.ok();
	}
}
