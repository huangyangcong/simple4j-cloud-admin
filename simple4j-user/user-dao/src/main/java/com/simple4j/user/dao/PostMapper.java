package com.simple4j.user.dao;

import java.util.List;

import com.simple4j.autoconfigure.mybatis.base.ExtendMapper;
import org.apache.ibatis.annotations.Param;
import com.simple4j.user.entity.Post;

/**
 * 岗位表 Mapper 接口
 *
 * @author Chill
 */
public interface PostMapper extends ExtendMapper<Post> {

	/**
	 * 获取岗位名
	 *
	 * @param userId
	 * @return
	 */
	List<String> getPostNames(@Param("userId") Long userId);

}
