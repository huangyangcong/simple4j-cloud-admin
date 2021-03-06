package com.simple4j.auth.service.impl;

import java.util.Set;

import com.simple4j.auth.mapper.UserRoleMapper;
import com.simple4j.auth.service.IUserRoleService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

/**
 * 服务类
 *
 * @author hyc
 */
@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements IUserRoleService {

	private final UserRoleMapper userRoleMapper;

	@Override
	public Set<String> getRoleIds(String userId) {
		return userRoleMapper.getRoleIds(userId);
	}
}
