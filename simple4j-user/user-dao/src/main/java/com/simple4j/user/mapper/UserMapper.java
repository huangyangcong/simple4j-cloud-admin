package com.simple4j.user.mapper;

import java.util.List;

import com.simple4j.autoconfigure.mybatis.base.ExtendMapper;
import com.simple4j.user.entity.User;

/**
 * Mapper 接口
 *
 * @author Chill
 */
public interface UserMapper extends ExtendMapper<User> {

	/**
	 * 获取用户
	 *
	 * @param tenantId
	 * @param account
	 * @param password
	 * @return
	 */
	User getUser(String tenantId, String account, String password);

	/**
	 * 获取角色别名
	 *
	 * @param ids
	 * @return
	 */
	List<String> getRoleAlias(List<Long> ids);

	/**
	 * 获取部门名
	 *
	 * @param ids
	 * @return
	 */
	List<String> getDeptName(List<Long> ids);
}
