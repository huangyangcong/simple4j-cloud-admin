package com.simple4j.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.simple4j.common.orm.BaseEntity;

/**
 * 顶部菜单实体类
 *
 * @author Blade
 * @since 2020-08-26
 */
@Data
@TableName("simple4j_navbar_menu")
public class NavbarMenu extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 按钮编号
	 */
	private Long menuId;
	/**
	 * 顶部按钮编号
	 */
	private Long NavbarId;
	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.ASSIGN_ID)
	private Long id;


}
