package com.simple4j.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.simple4j.common.orm.BaseEntity;

/**
 * 实体类
 *
 * @author Blade
 * @since 2020-08-26
 */
@Data
@TableName("simple4j_navbar")
public class Navbar extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 菜单排序
	 */
	private Integer sort;
	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.ASSIGN_ID)
	private Long id;
	/**
	 * 按钮编号
	 */
	private String name;
	/**
	 * 租户编号
	 */
	private String code;
	/**
	 * 图标
	 */
	private String icon;
	/**
	 * 租户编号
	 */
	private String tenantId;
}
