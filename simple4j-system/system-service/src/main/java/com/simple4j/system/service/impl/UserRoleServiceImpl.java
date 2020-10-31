package com.simple4j.system.service.impl;


import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.simple4j.system.entity.UserRole;
import com.simple4j.system.mapper.UserRoleMapper;
import com.simple4j.system.request.UserRoleGrantRequest;
import com.simple4j.system.service.IUserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 服务类
 *
 * @author hyc
 */
@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements
	IUserRoleService {

	private final UserRoleMapper userRoleMapper;

	@Override
	public Set<String> getRoleIds(String userId) {
		return userRoleMapper.getRoleIds(userId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void grant(UserRoleGrantRequest userRoleGrantRequest) {
		grant(userRoleGrantRequest.getUserIds(), userRoleGrantRequest.getRoleIds());
	}

	@Override
	public void grant(Set<String> userIds, Set<String> roleIds) {
		if (CollUtil.isNotEmpty(userIds)) {
			userRoleMapper
				.physicsDelete(
					Wrappers.<UserRole>lambdaQuery().in(UserRole::getUserId, userIds));
			if (CollUtil.isNotEmpty(roleIds)) {
				List<UserRole> userRoles = new ArrayList<>();
				for (String userId : userIds) {
					for (String roleId : roleIds) {
						UserRole userRole = new UserRole();
						userRole.setUserId(userId);
						userRole.setRoleId(roleId);
						userRoles.add(userRole);
					}
				}
				userRoleMapper.saveBatch(userRoles);
			}
		}
	}
}
