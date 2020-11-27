package com.simple4j.auth.mapper;

import com.simple4j.auth.entity.RoleMenu;
import com.simple4j.autoconfigure.mybatis.base.ExtendMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

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
