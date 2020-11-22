package com.simple4j.system.mapper;

import java.util.Set;

import com.simple4j.autoconfigure.mybatis.base.ExtendMapper;
import com.simple4j.system.entity.UserPost;

/**
 * Mapper 接口
 *
 * @author hyc
 */
public interface UserPostMapper extends ExtendMapper<UserPost> {

	/**
	 * 获取用户岗位id
	 *
	 * @param userId
	 * @return
	 */
	Set<String> getPostIds(String userId);
}
