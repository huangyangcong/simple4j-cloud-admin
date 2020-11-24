package com.simple4j.auth.mapper;

import java.util.Set;

import com.simple4j.autoconfigure.mybatis.base.ExtendMapper;
import com.simple4j.auth.entity.RoleMenu;
import org.apache.ibatis.annotations.Param;

/**
 * Mapper 接口
 *
 * @author hyc
 */
public interface RoleMenuMapper extends ExtendMapper<RoleMenu> {

	/**
	 * 获取菜单别名（权限）
	 *
	 * @param roleIds
	 * @return
	 */
	Set<String> permissions(@Param("roleIds") Set<String> roleIds);
}
