package com.simple4j.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.simple4j.autoconfigure.mybatis.base.BaseEntity;
import lombok.Data;

/**
 * 角色菜单关联
 *
 * @author hyc
 */
@Data
@TableName("simple4j_role_menu")
public class RoleMenu extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.ASSIGN_ID)
	private String id;

	/**
	 * 菜单id
	 */
	private String menuId;

	/**
	 * 角色id
	 */
	private String roleId;
}
