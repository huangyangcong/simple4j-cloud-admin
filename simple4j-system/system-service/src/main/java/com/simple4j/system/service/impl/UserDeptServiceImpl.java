package com.simple4j.system.service.impl;


import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.simple4j.system.entity.UserDept;
import com.simple4j.system.mapper.UserDeptMapper;
import com.simple4j.system.request.UserDeptGrantRequest;
import com.simple4j.system.service.IUserDeptService;
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
public class UserDeptServiceImpl implements
	IUserDeptService {

	private final UserDeptMapper userDeptMapper;

	@Override
	public Set<String> getDeptIds(String userId) {
		return userDeptMapper.getDeptIds(userId);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void grant(UserDeptGrantRequest userDeptGrantRequest) {
		grant(userDeptGrantRequest.getUserIds(), userDeptGrantRequest.getDeptIds());
	}

	@Override
	public void grant(Set<String> userIds, Set<String> deptIds) {
		if (CollUtil.isNotEmpty(userIds)) {
			userDeptMapper
				.physicsDelete(Wrappers.<UserDept>lambdaQuery().in(UserDept::getUserId, userIds));
			if (CollUtil.isNotEmpty(deptIds)) {
				List<UserDept> userDepts = new ArrayList<>();
				for (String userId : userIds) {
					for (String deptId : deptIds) {
						UserDept userDept = new UserDept();
						userDept.setUserId(userId);
						userDept.setDeptId(deptId);
						userDepts.add(userDept);
					}
				}
				userDeptMapper.saveBatch(userDepts);
			}
		}
	}

	@Override
	public void removeByDeptIds(Set<String> deptIds) {
		if (CollUtil.isNotEmpty(deptIds)) {
			userDeptMapper.physicsDelete(
				Wrappers.<UserDept>lambdaQuery().in(UserDept::getDeptId, deptIds));
		}
	}

	@Override
	public void removeByUserIds(Set<String> userIds) {
		if (CollUtil.isNotEmpty(userIds)) {
			userDeptMapper.physicsDelete(
				Wrappers.<UserDept>lambdaQuery().in(UserDept::getUserId, userIds));
		}
	}
}
