package com.simple4j.user.common.util;

import java.util.List;

import com.google.common.collect.Lists;
import com.simple4j.user.dto.JwtDto;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author hyc
 */
@Slf4j
public class SecurityUtils {

	/**
	 * 获取当前登录的用户
	 *
	 * @return UserDetails
	 */
	public static JwtDto getCurrentUser() {
		final Authentication authentication = SecurityContextHolder.getContext()
			.getAuthentication();
		if (authentication == null) {
			return null;
		}
		return (JwtDto) authentication.getPrincipal();
	}

	/**
	 * 获取系统用户名称
	 *
	 * @return 系统用户名称
	 */
	public static String getCurrentUsername() {
		JwtDto jwtDto = getCurrentUser();
		if (jwtDto == null) {
			return null;
		}
		return jwtDto.getUserInfo().getName();
	}

	/**
	 * 获取系统用户ID
	 *
	 * @return 系统用户ID
	 */
	public static Long getCurrentUserId() {
		JwtDto jwtDto = getCurrentUser();
		if (jwtDto == null) {
			return null;
		}
		return jwtDto.getUserInfo().getId();
	}

	/**
	 * 获取系统用户租户ID
	 *
	 * @return 系统用户租户ID
	 */
	public static String getTenantId() {
		JwtDto jwtDto = getCurrentUser();
		if (jwtDto == null) {
			return null;
		}
		return jwtDto.getUserInfo().getTenantId();
	}

	/**
	 * 获取当前用户的数据权限
	 *
	 * @return /
	 */
	public static List<String> getCurrentUserDataScope() {
		JwtDto jwtDto = getCurrentUser();
		if (jwtDto == null) {
			return Lists.newArrayList();
		}
		return jwtDto.getUserInfo().getPermissions();
	}

	/**
	 * 获取当前用户的数据权限
	 *
	 * @return /
	 */
	public static List<Long> getCurrentUserDataRoles() {
		JwtDto jwtDto = getCurrentUser();
		if (jwtDto == null) {
			return Lists.newArrayList();
		}
		return jwtDto.getUserInfo().getRoles();
	}

	/**
	 * 是否admin
	 *
	 * @return
	 */
	public static boolean isAdministrator() {
		return getCurrentUserDataScope().contains("admin");
	}

	public static boolean isTenantAdmin() {
		return SecurityUtils.getCurrentUserId() == null || SecurityUtils.getCurrentUserId() == 0L;
	}
}
