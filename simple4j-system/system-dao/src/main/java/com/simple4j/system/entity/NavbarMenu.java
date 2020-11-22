package com.simple4j.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.simple4j.autoconfigure.mybatis.base.BaseEntity;
import lombok.Data;

/**
 * 顶部菜单实体类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@TableName("simple4j_navbar_menu")
public class NavbarMenu extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 按钮编号
	 */
	private String menuId;
	/**
	 * 顶部按钮编号
	 */
	private String NavbarId;
	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.ASSIGN_ID)
	private String id;
}
