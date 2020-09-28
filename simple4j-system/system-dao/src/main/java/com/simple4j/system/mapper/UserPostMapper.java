package com.simple4j.system.mapper;

import java.util.List;

import com.simple4j.autoconfigure.mybatis.base.ExtendMapper;
import com.simple4j.system.entity.UserPost;

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
	List<Long> getPostIds(Long userId);
}