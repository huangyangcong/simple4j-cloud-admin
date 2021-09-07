package com.simple4j.auth.mapper;

import com.simple4j.auth.entity.UserRole;
import com.simple4j.autoconfigure.mybatis.base.ExtendMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
	List<String> getRoleIds(@Param("userId") String userId);
}
