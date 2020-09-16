package com.simple4j.user.service.impl;


import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.simple4j.user.mapper.UserRoleMapper;
import com.simple4j.user.entity.UserRole;
import com.simple4j.user.request.UserRoleGrantRequest;
import com.simple4j.user.service.IUserRoleService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 服务类
 *
 * @author Chill
 */
@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements
		IUserRoleService {

	private final UserRoleMapper userRoleMapper;

	@Override
	public List<Long> getRoleIds(Long userId) {
		return userRoleMapper.getRoleIds(userId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void grant(UserRoleGrantRequest userRoleGrantRequest) {
		grant(userRoleGrantRequest.getUserIds(), userRoleGrantRequest.getRoleIds());
	}

	@Override
	public void grant(List<Long> userIds, List<Long> roleIds) {
		if (CollUtil.isNotEmpty(userIds)) {
			userRoleMapper
					.physicsDelete(
							Wrappers.<UserRole>lambdaQuery().in(UserRole::getUserId, userIds));
			if (CollUtil.isNotEmpty(roleIds)) {
				List<UserRole> userRoles = new ArrayList<>();
				for (Long userId : userIds) {
					for (Long roleId : roleIds) {
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
