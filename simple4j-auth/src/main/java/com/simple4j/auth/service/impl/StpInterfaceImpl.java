package com.simple4j.auth.service.impl;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.session.SaSessionCustomUtil;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.temp.SaTempUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.simple4j.auth.service.IRoleMenuService;
import com.simple4j.auth.service.IUserRoleService;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StpInterfaceImpl implements StpInterface {
	private final IUserRoleService userRoleService;
	private final IRoleMenuService roleMenuService;

	@Override
	public List<String> getPermissionList(Object loginId, String loginType) {
		List<String> permissions = new ArrayList<>();
		// 1. 获取这个账号所属角色id
		List<String> roles = getRoleList(loginId, loginType);
		// 2. 获取这个角色id拥有的权限列表
		for (String role : roles) {
			SaSession roleSession = SaSessionCustomUtil.getSessionById("role-" + role);
			List<String> list = roleSession.get("Permission_List", () -> roleMenuService.getPermission(Sets.newHashSet(role)));
			permissions.addAll(list);
		}
		// 3. 返回
		return permissions;
	}

	@Override
	public List<String> getRoleList(Object loginId, String loginType) {
		//临时账号
		String tempId = null;
		if (loginId instanceof String) {
			try {
				tempId = SaTempUtil.parseToken((String) loginId, String.class);
			} catch (SignatureException e) {

			}
		}
		if (StrUtil.isNotEmpty(tempId)) {
			return Lists.newArrayList("temp");
		}
		return StpUtil.getSessionByLoginId(loginId).get("Role_Id", () -> userRoleService.getRoleIds((String) loginId));
	}
}
