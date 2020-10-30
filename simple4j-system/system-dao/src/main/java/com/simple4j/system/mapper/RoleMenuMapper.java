package com.simple4j.system.mapper;

import java.util.List;
import java.util.Set;

import com.simple4j.autoconfigure.mybatis.base.ExtendMapper;
import org.apache.ibatis.annotations.Param;
import com.simple4j.system.entity.RoleMenu;

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
