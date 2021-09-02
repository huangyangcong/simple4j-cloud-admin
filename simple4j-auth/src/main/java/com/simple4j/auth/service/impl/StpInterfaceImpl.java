package com.simple4j.auth.service.impl;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.session.SaSessionCustomUtil;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.simple4j.auth.service.IRoleMenuService;
import com.simple4j.auth.service.IUserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StpInterfaceImpl implements StpInterface {
	private final IUserRoleService userRoleService;
	private final IRoleMenuService roleMenuService;
	@Override
	public List<String> getPermissionList(Object loginId, String loginType) {

		// 1. 获取这个账号所属角色id
		long roleId = StpUtil.getSessionByLoginId(loginId).get("Role_Id", () -> {
			return new ArrayList<>();     // 从数据库查询这个账号所属的角色id
		});

		// 2. 获取这个角色id拥有的权限列表
		SaSession roleSession = SaSessionCustomUtil.getSessionById("role-" + roleId);
		List<String> list = roleSession.get("Permission_List", () -> {
			return new ArrayList<>();  // 从数据库查询这个角色id拥有的权限列表
		});

		// 3. 返回
		return list;
	}

	@Override
	public List<String> getRoleList(Object loginId, String loginType) {
		return userRoleService.getRoleIds((String)loginId);
	}
}
