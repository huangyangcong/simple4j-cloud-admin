package com.simple4j.user.controller;


import cn.hutool.core.util.StrUtil;
import com.simple4j.autoconfigure.jwt.service.AbstractUserDetailsService;
import com.simple4j.user.base.Page;
import com.simple4j.autoconfigure.jwt.annotation.AnonymousAccess;
import com.simple4j.user.request.*;
import com.simple4j.user.response.UserDetailResponse;
import com.simple4j.user.response.UserLoginResponse;
import com.simple4j.user.service.IUserRoleService;
import com.simple4j.user.service.IUserService;
import com.simple4j.web.bean.ApiResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 控制器
 *
 * @author Chill
 */
@RestController
@RequestMapping("/user/api/v1")
@AllArgsConstructor
public class UserController {

	private final IUserService userService;
	private final AbstractUserDetailsService userDetailsService;
	private final IUserRoleService userRoleService;
	private final UserMapStruct userMapStruct;
	private final RedisTemplate redisTemplate;

	/**
	 * 用户登录
	 */
	@ApiOperation(value = "登录")
	@PostMapping("/login")
	@AnonymousAccess
	public ApiResponse<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest) {
		//TODO 用户验证码

		String key = userLoginRequest.getCaptchaKey();
		String code = userLoginRequest.getCaptchaCode();
		// 获取验证码
		String redisCode = String
			.valueOf(redisTemplate.opsForValue().get(CacheNames.CAPTCHA_KEY + key));
		// 判断验证码
		if (code == null || !StrUtil.equalsIgnoreCase(redisCode, code)) {
//			throw new BusinessException("验证码错误");
		}

		String token = userDetailsService.usernameAndPasswordAuth(userLoginRequest.getUsername(),
			userLoginRequest.getPassword());
		JwtDto jwtUserDto = (JwtDto) SecurityContextHolder.getContext().getAuthentication()
			.getPrincipal();
		UserLoginResponse userLoginResponse = userMapStruct
			.toVo(jwtUserDto.getUserInfo());
		userLoginResponse.setToken(token);
		return ApiResponse.ok(userLoginResponse);
	}

	/**
	 * 登出
	 */
	@ApiOperation(value = "登出")
	@PostMapping("/logout")
	@AnonymousAccess
	public ApiResponse logout() {
		userDetailsService.logout(SecurityUtils.getCurrentUsername());
		return ApiResponse.ok();
	}

	/**
	 * 查询单条
	 */
	@ApiOperation(value = "查看详情", notes = "传入id")
	@PostMapping("/detail")
	public ApiResponse<UserDetailResponse> detail(@Valid @RequestBody UserDetailRequest userDetailRequest) {
		UserDetailResponse userDetailResponse = userService.detail(userDetailRequest);
		return ApiResponse.ok(userDetailResponse);
	}

	/**
	 * 查询单条
	 */
	@ApiOperation(value = "查看详情", notes = "传入id")
	@PostMapping("/info")
	public ApiResponse<UserInfo> info() {
		return ApiResponse.ok(SecurityUtils.getCurrentUser().getUserInfo());
	}

	/**
	 * 用户列表
	 */
	@PostMapping("/page")
	@ApiOperation(value = "列表", notes = "传入account和realName")
	public ApiResponse<Page<UserDetailResponse>> page(@Valid @RequestBody UserPageRequest userPageRequest) {
		return ApiResponse.ok(userService.page(userPageRequest));
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperation(value = "新增或修改", notes = "传入User")
	public ApiResponse submit(@Valid @RequestBody UserAddRequest userAddRequest) {
		userService.submit(userAddRequest);
		return ApiResponse.ok();
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperation(value = "修改", notes = "传入User")
	public ApiResponse update(@Valid @RequestBody UserUpdateRequest userUpdateRequest) {
		userUpdateRequest.setTenantId(SecurityUtils.getTenantId());
		userService.update(userUpdateRequest);
		return ApiResponse.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperation(value = "删除", notes = "传入地基和")
	public ApiResponse remove(@Valid @RequestBody UserRemoveRequest userRemoveRequest) {
		userService.remove(userRemoveRequest);
		return ApiResponse.ok();
	}


	/**
	 * 设置菜单权限
	 *
	 * @param userRoleGrantRequest
	 * @return
	 */
	@PostMapping("/grant")
	@ApiOperation(value = "权限设置", notes = "传入roleId集合以及menuId集合")
	public ApiResponse grant(@Valid @RequestBody UserRoleGrantRequest userRoleGrantRequest) {
		userRoleService.grant(userRoleGrantRequest);
		return ApiResponse.ok();
	}

	@PostMapping("/reset-password")
	@ApiOperation(value = "初始化密码", notes = "传入userId集合")
	public ApiResponse resetPassword(UserResetPasswordRequest userResetPasswordRequest) {
		boolean temp = userService.resetPassword(userResetPasswordRequest);
		return R.status(temp);
	}

	/**
	 * 修改密码
	 *
	 * @param oldPassword
	 * @param newPassword
	 * @param newPassword1
	 * @return
	 */
	@PostMapping("/update-password")
	@ApiOperation(value = "修改密码", notes = "传入密码")
	public ApiResponse updatePassword(SecurityUtils user,
							@ApiParam(value = "旧密码", required = true) @RequestParam String oldPassword,
							@ApiParam(value = "新密码", required = true) @RequestParam String newPassword,
							@ApiParam(value = "新密码", required = true) @RequestParam String newPassword1) {
		boolean temp = userService
			.updatePassword(SecurityUtils.getCurrentUserId(), oldPassword, newPassword,
				newPassword1);
		return R.status(temp);
	}

	/**
	 * 导入用户
	 */
	@PostMapping("import-user")
	@ApiOperation(value = "导入用户", notes = "传入excel")
	public ApiResponse importUser(MultipartFile file, Integer isCovered) {
		String filename = file.getOriginalFilename();
		if (StringUtils.isEmpty(filename)) {
			throw new RuntimeException("请上传文件!");
		}
		if ((!StringUtils.endsWithIgnoreCase(filename, ".xls") && !StringUtils
			.endsWithIgnoreCase(filename, ".xlsx"))) {
			throw new RuntimeException("请上传正确的excel文件!");
		}
		InputStream inputStream;
		try {
			UserImportListener importListener = new UserImportListener(userService);
			inputStream = new BufferedInputStream(file.getInputStream());
			ExcelReaderBuilder builder = EasyExcel
				.read(inputStream, UserExcel.class, importListener);
			builder.doReadAll();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return R.ok("操作成功");
	}

	/**
	 * 导出用户
	 */
	@SneakyThrows
	@PostMapping("export-user")
	@ApiOperation(value = "导出用户", notes = "传入user")
	public void exportUser(@ApiIgnore UserListRequest userListRequest,
						   HttpServletResponse response) {
		LambdaQueryWrapper<User> queryWrapper = Wrappers.<User>lambdaQuery()
			.eq(StrUtil.isNotEmpty(userListRequest.getAccount()), User::getAccount,
				userListRequest.getAccount())
			.eq(StrUtil.isNotEmpty(userListRequest.getRealName()), User::getRealName,
				userListRequest.getRealName());
		if (!SecurityUtils.isAdministrator()) {
			queryWrapper.eq(User::getTenantId, SecurityUtils.getTenantId());
		}
		queryWrapper.eq(User::getIsDeleted, CommonConstant.DB_NOT_DELETED);
		List<UserExcel> list = userService.exportUser(queryWrapper);
		response.setContentType("application/vnd.ms-excel");
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
		String fileName = URLEncoder.encode("用户数据导出", StandardCharsets.UTF_8.name());
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
		EasyExcel.write(response.getOutputStream(), UserExcel.class).sheet("用户数据表").doWrite(list);
	}

	/**
	 * 导出模板
	 */
	@SneakyThrows
	@PostMapping("export-template")
	@ApiOperation(value = "导出模板")
	public void exportUser(HttpServletResponse response) {
		List<UserExcel> list = new ArrayList<>();
		response.setContentType("application/vnd.ms-excel");
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
		String fileName = URLEncoder.encode("用户数据模板", StandardCharsets.UTF_8.name());
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
		EasyExcel.write(response.getOutputStream(), UserExcel.class).sheet("用户数据表").doWrite(list);
	}

	/**
	 * 第三方注册用户
	 */
	@PostMapping("/register-guest")
	@ApiOperation(value = "第三方注册用户", notes = "传入user")
	public ApiResponse registerGuest(User user, Long oauthId) {
		return R.status(userService.registerGuest(user, oauthId));
	}
}
