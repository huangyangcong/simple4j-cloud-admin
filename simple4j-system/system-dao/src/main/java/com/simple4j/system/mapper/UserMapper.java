package com.simple4j.system.mapper;

import java.util.List;

import com.simple4j.autoconfigure.mybatis.base.ExtendMapper;
import com.simple4j.system.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * Mapper 接口
 *
 * @author hyc
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
