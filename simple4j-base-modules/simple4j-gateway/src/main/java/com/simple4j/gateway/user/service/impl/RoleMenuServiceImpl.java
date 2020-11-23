package com.simple4j.gateway.user.service.impl;

import java.util.Set;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Sets;
import com.simple4j.gateway.user.mapper.RoleMenuMapper;
import com.simple4j.gateway.user.service.IRoleMenuService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author hyc
 */
@Service
@RequiredArgsConstructor
public class RoleMenuServiceImpl implements IRoleMenuService {

	private final RoleMenuMapper roleMenuMapper;

	@Override
	public Set<String> getPermission(Set<String> roleIds) {
		if (CollUtil.isEmpty(roleIds)) {
			return Sets.newHashSet();
		}
		return roleMenuMapper.permissions(roleIds);
	}
}
