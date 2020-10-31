package com.simple4j.system.service.impl;


import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.simple4j.system.entity.UserPost;
import com.simple4j.system.mapper.UserPostMapper;
import com.simple4j.system.request.UserPostGrantRequest;
import com.simple4j.system.service.IUserPostService;
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
public class UserPostServiceImpl implements IUserPostService {
	private final UserPostMapper userPostMapper;

	@Override
	public Set<String> getPostIds(String userId) {
		return userPostMapper.getPostIds(userId);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void grant(UserPostGrantRequest userPostGrantRequest) {
		grant(userPostGrantRequest.getUserIds(), userPostGrantRequest.getPostIds());
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void grant(Set<String> userIds, Set<String> postIds) {
		if (CollUtil.isNotEmpty(userIds)) {
			userPostMapper
				.physicsDelete(Wrappers.<UserPost>lambdaQuery().in(UserPost::getUserId, userIds));
			if (CollUtil.isNotEmpty(postIds)) {
				List<UserPost> userPosts = new ArrayList<>();
				for (String userId : userIds) {
					for (String pstId : postIds) {
						UserPost userPost = new UserPost();
						userPost.setUserId(userId);
						userPost.setPostId(pstId);
						userPosts.add(userPost);
					}
				}
				userPostMapper.saveBatch(userPosts);
			}
		}
	}

	@Override
	public void removeByPostIds(Set<String> postIds) {
		if (CollUtil.isNotEmpty(postIds)) {
			userPostMapper.physicsDelete(
				Wrappers.<UserPost>lambdaQuery().in(UserPost::getPostId, postIds));
		}
	}

	@Override
	public void removeByUserIds(Set<String> userIds) {
		if (CollUtil.isNotEmpty(userIds)) {
			userPostMapper.physicsDelete(
				Wrappers.<UserPost>lambdaQuery().in(UserPost::getUserId, userIds));
		}
	}
}
