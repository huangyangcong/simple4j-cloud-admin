package com.simple4j.system.mapper;

import java.util.List;
import java.util.Set;

import com.simple4j.autoconfigure.mybatis.base.ExtendMapper;
import org.apache.ibatis.annotations.Param;
import com.simple4j.system.entity.UserRole;

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
