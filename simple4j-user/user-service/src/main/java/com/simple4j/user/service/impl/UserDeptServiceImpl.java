package com.simple4j.user.service.impl;


import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simple4j.user.service.IUserDeptService;
import com.simple4j.user.entity.UserDept;
import com.simple4j.user.mapper.UserDeptMapper;
import com.simple4j.user.request.UserDeptGrantRequest;
import com.simple4j.user.service.IUserDeptService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 服务类
 *
 * @author Chill
 */
@Service
public class UserDeptServiceImpl extends ServiceImpl<UserDeptMapper, UserDept> implements
		IUserDeptService {

	@Override
	public List<Long> getDeptIds(Long userId) {
		return baseMapper.getDeptIds(userId);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void grant(UserDeptGrantRequest userDeptGrantRequest) {
		grant(userDeptGrantRequest.getUserIds(), userDeptGrantRequest.getDeptIds());
	}

	@Override
	public void grant(List<Long> userIds, List<Long> deptIds) {
		if (CollUtil.isNotEmpty(userIds)) {
			baseMapper
				.physicsDelete(Wrappers.<UserDept>lambdaQuery().in(UserDept::getUserId, userIds));
			if (CollUtil.isNotEmpty(deptIds)) {
				List<UserDept> userDepts = new ArrayList<>();
				for (Long userId : userIds) {
					for (Long deptId : deptIds) {
						UserDept userDept = new UserDept();
						userDept.setUserId(userId);
						userDept.setDeptId(deptId);
						userDepts.add(userDept);
					}
				}
				this.saveBatch(userDepts);
			}
		}
	}

	@Override
	public void removeByDeptIds(List<String> deptIds) {
		if (CollUtil.isNotEmpty(deptIds)) {
			baseMapper.physicsDelete(
					Wrappers.<UserDept>lambdaQuery().in(UserDept::getDeptId, deptIds));
		}
	}
	@Override
	public void removeByUserIds(List<String> userIds) {
		if (CollUtil.isNotEmpty(userIds)) {
			baseMapper.physicsDelete(
					Wrappers.<UserDept>lambdaQuery().in(UserDept::getUserId, userIds));
		}
	}
}
