package com.simple4j.system.mapper;

import java.util.List;
import java.util.Set;

import com.simple4j.autoconfigure.mybatis.base.ExtendMapper;
import org.apache.ibatis.annotations.Param;
import com.simple4j.system.dto.MenuDTO;
import com.simple4j.system.entity.Menu;

/**
 * Mapper 接口
 *
 * @author Chill
 */
public interface MenuMapper extends ExtendMapper<Menu> {

	/**
	 * 树形结构
	 *
	 * @return
	 */
	List<Menu> tree();

	/**
	 * 授权树形结构
	 *
	 * @return
	 */
	List<Menu> grantTree();

	/**
	 * 授权树形结构
	 *
	 * @param roleId
	 * @return
	 */
	List<Menu> grantTreeByRole(@Param("list") Set<String> roleId);

	/**
	 * 所有菜单
	 *
	 * @return
	 */
	List<Menu> allMenu();

	/**
	 * 权限配置菜单
	 *
	 * @param roleId
	 * @return
	 */
	List<Menu> roleMenu(@Param("list") Set<Long> roleId);

	/**
	 * 菜单树形结构
	 *
	 * @param navbarId
	 * @param roleIds
	 * @return
	 */
	List<Menu> routes(@Param("navbarId") Long navbarId, @Param("roleIds") Set<String> roleIds);

	/**
	 * 按钮树形结构
	 *
	 * @param roleIds
	 * @return
	 */
	List<Menu> buttons(@Param("roleIds") Set<String> roleIds);

	/**
	 * 获取配置的角色权限
	 *
	 * @param roleIds
	 * @return
	 */
	List<MenuDTO> authRoutes(@Param("roleIds") Set<String> roleIds);
}
