package com.simple4j.system.mapper;

import com.simple4j.autoconfigure.mybatis.base.ExtendMapper;
import com.simple4j.system.entity.UserPost;

import java.util.Set;

/**
 * Mapper 接口
 *
 * @author Chill
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
