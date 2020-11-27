package com.simple4j.system.mapper;

import com.simple4j.autoconfigure.mybatis.base.ExtendMapper;
import com.simple4j.system.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * Mapper 接口
 *
 * @author hyc
 */
public interface RoleMapper extends ExtendMapper<Role> {

	/**
	 * 获取树形节点
	 *
	 * @param tenantId
	 * @param excludeRole
	 * @return
	 */
	List<Role> tree(@Param("tenantId") String tenantId, @Param("excludeRole") String excludeRole);

	/**
	 * 获取角色名
	 *
	 * @param userId
	 * @return
	 */
	List<String> getRoleNames(@Param("userId") String userId);

	/**
	 * 获取角色别名
	 *
	 * @param userId
	 * @return
	 */
	Set<String> getRoleAlias(String userId);
}
