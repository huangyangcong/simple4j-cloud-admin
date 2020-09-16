package com.simple4j.user.mapper;

import java.util.List;

import com.simple4j.autoconfigure.mybatis.base.ExtendMapper;
import org.apache.ibatis.annotations.Param;
import com.simple4j.user.entity.RoleMenu;

/**
 * Mapper 接口
 *
 * @author Chill
 */
public interface RoleMenuMapper extends ExtendMapper<RoleMenu> {

	/**
	 * 获取菜单别名（权限）
	 *
	 * @param roleIds
	 * @return
	 */
	List<String> permissions(@Param("roleIds") List<Long> roleIds);
}
