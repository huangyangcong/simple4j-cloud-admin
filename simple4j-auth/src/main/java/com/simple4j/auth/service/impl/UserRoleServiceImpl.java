package com.simple4j.auth.service.impl;

import com.simple4j.auth.mapper.UserRoleMapper;
import com.simple4j.auth.service.IUserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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
	public List<String> getRoleIds(String userId) {
		return userRoleMapper.getRoleIds(userId);
	}
}
