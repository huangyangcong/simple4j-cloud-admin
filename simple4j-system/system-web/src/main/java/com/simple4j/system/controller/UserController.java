package com.simple4j.system.controller;

import com.simple4j.api.base.Page;
import com.simple4j.system.request.UserAddRequest;
import com.simple4j.system.request.UserDetailRequest;
import com.simple4j.system.request.UserListRequest;
import com.simple4j.system.request.UserLoginRequest;
import com.simple4j.system.request.UserPageRequest;
import com.simple4j.system.request.UserRegisterGuestRequest;
import com.simple4j.system.request.UserRemoveRequest;
import com.simple4j.system.request.UserResetPasswordRequest;
import com.simple4j.system.request.UserRoleGrantRequest;
import com.simple4j.system.request.UserUpdateRequest;
import com.simple4j.system.response.UserDetailResponse;
import com.simple4j.system.response.UserInfo;
import com.simple4j.system.response.UserLoginResponse;
import com.simple4j.system.service.IUserRoleService;
import com.simple4j.system.service.IUserService;
import com.simple4j.web.bean.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

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
@Api(tags = "用户管理")
public class UserController {

  private final IUserService userService;
  private final IUserRoleService userRoleService;

  @ApiOperation(value = "登录")
  @PostMapping("/login")
  @PermitAll()
  public ApiResponse<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest) {
    UserLoginResponse userLoginResponse = userService.login(userLoginRequest);
    return ApiResponse.ok(userLoginResponse);
  }

  @ApiOperation(value = "登出")
  @PostMapping("/logout")
  @PreAuthorize("permitAll()")
  public ApiResponse<Void> logout() {
    userService.logout("");
    return ApiResponse.ok();
  }

  @ApiOperation(value = "查看详情", notes = "传入id")
  @PostMapping("/detail")
  public ApiResponse<UserDetailResponse> detail(
      @Valid @RequestBody UserDetailRequest userDetailRequest) {
    UserDetailResponse userDetailResponse = userService.detail(userDetailRequest);
    return ApiResponse.ok(userDetailResponse);
  }

  @ApiOperation(value = "查看详情", notes = "传入id")
  @PostMapping("/info")
  public ApiResponse<UserInfo> info() {
    return ApiResponse.ok(userService.currentUserInfo());
  }

  @PostMapping("/page")
  @ApiOperation(value = "列表", notes = "传入account和realName")
  public ApiResponse<Page<UserDetailResponse>> page(
      @Valid @RequestBody UserPageRequest userPageRequest) {
    return ApiResponse.ok(userService.page(userPageRequest));
  }

  @PostMapping("/submit")
  @ApiOperation(value = "新增或修改", notes = "传入User")
  public ApiResponse<Void> submit(@Valid @RequestBody UserAddRequest userAddRequest) {
    userService.submit(userAddRequest);
    return ApiResponse.ok();
  }

  @PostMapping("/update")
  @ApiOperation(value = "修改", notes = "传入User")
  public ApiResponse<Void> update(@Valid @RequestBody UserUpdateRequest userUpdateRequest) {
    userService.update(userUpdateRequest);
    return ApiResponse.ok();
  }

  @PostMapping("/remove")
  @ApiOperation(value = "删除", notes = "传入地基和")
  public ApiResponse<Void> remove(@Valid @RequestBody UserRemoveRequest userRemoveRequest) {
    userService.remove(userRemoveRequest);
    return ApiResponse.ok();
  }

  @PostMapping("/grant")
  @ApiOperation(value = "权限设置", notes = "传入roleId集合以及menuId集合")
  public ApiResponse<Void> grant(@Valid @RequestBody UserRoleGrantRequest userRoleGrantRequest) {
    userRoleService.grant(userRoleGrantRequest);
    return ApiResponse.ok();
  }

  @PostMapping("/reset-password")
  @ApiOperation(value = "初始化密码", notes = "传入userId集合")
  public ApiResponse<Void> resetPassword(UserResetPasswordRequest userResetPasswordRequest) {
    userService.resetPassword(userResetPasswordRequest);
    return ApiResponse.ok();
  }

  @PostMapping("/update-password")
  @ApiOperation(value = "修改密码", notes = "传入密码")
  public ApiResponse<Void> updatePassword(
      @ApiParam(value = "旧密码", required = true) @RequestParam String oldPassword,
      @ApiParam(value = "新密码", required = true) @RequestParam String newPassword,
      @ApiParam(value = "新密码", required = true) @RequestParam String newPassword1) {
    userService.updatePassword(oldPassword, newPassword, newPassword1);
    return ApiResponse.ok();
  }

  /** 导入用户 */
  @PostMapping("import-user")
  @ApiOperation(value = "导入用户", notes = "传入excel")
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

  /** 导出用户 */
  @SneakyThrows
  @PostMapping("export-user")
  @ApiOperation(value = "导出用户", notes = "传入user")
  public void exportUser(@ApiIgnore UserListRequest userListRequest, HttpServletResponse response) {
    response.setContentType("application/vnd.ms-excel");
    response.setCharacterEncoding(StandardCharsets.UTF_8.name());
    String fileName = URLEncoder.encode("用户数据导出", StandardCharsets.UTF_8.name());
    response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
    userService.exportUser(response.getOutputStream(), userListRequest);
  }

  /** 导出模板 */
  @SneakyThrows
  @PostMapping("export-template")
  @ApiOperation(value = "导出模板")
  public void exportUser(HttpServletResponse response) {
    response.setContentType("application/vnd.ms-excel");
    response.setCharacterEncoding(StandardCharsets.UTF_8.name());
    String fileName = URLEncoder.encode("用户数据模板", StandardCharsets.UTF_8.name());
    response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
    userService.exportUser(response.getOutputStream());
  }

  /** 第三方注册用户 */
  @PostMapping("/register-guest")
  @ApiOperation(value = "第三方注册用户", notes = "传入user")
  public ApiResponse<Void> registerGuest(
      @Valid @RequestBody UserRegisterGuestRequest userRegisterGuestRequest) {
    userService.registerGuest(userRegisterGuestRequest);
    return ApiResponse.ok();
  }

	/** 删除 */
	@GetMapping("/login2")
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public ApiResponse<Void> auth(@RegisteredOAuth2AuthorizedClient("baidu-client-auth-code") OAuth2AuthorizedClient authorizedClient,
								  @AuthenticationPrincipal OAuth2User oauth2User) {

		return ApiResponse.ok();
	}
}
