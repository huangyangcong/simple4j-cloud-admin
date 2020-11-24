package com.simple4j.auth.mapper;

import java.util.Set;

import com.simple4j.autoconfigure.mybatis.base.ExtendMapper;
import com.simple4j.auth.entity.UserRole;
import org.apache.ibatis.annotations.Param;

/**
 * Mapper 接口
 *
 * @author hyc
 */
public interface UserRoleMapper extends ExtendMapper<UserRole> {

	/**
	 * 获取用户角色id
	 *
	 * @param userId
	 * @return
	 */
	Set<String> getRoleIds(@Param("userId") String userId);
}
