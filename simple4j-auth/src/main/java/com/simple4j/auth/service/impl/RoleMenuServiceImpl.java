package com.simple4j.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Sets;
import com.simple4j.auth.mapper.RoleMenuMapper;
import com.simple4j.auth.service.IRoleMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

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
