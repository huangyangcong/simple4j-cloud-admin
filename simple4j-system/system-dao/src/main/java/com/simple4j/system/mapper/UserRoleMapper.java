package com.simple4j.system.mapper;

import com.simple4j.autoconfigure.mybatis.base.ExtendMapper;
import com.simple4j.system.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

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
