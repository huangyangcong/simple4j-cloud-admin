package com.simple4j.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.simple4j.dao.base.BaseEntity;
import lombok.Data;

/**
 * 菜单实体类
 *
 * @author Chill
 */
@Data
@TableName("simple4j_menu")
public class Menu  extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.ASSIGN_ID)
	private String id;

	/**
	 * 菜单父主键
	 */
	private Long parentId;

	/**
	 * 菜单编号
	 */
	private String code;

	/**
	 * 菜单名称
	 */
	private String name;

	/**
	 * 菜单别名
	 */
	private String alias;

	/**
	 * 请求地址
	 */
	private String path;

	/**
	 * 菜单资源
	 */
	private String source;

	/**
	 * 排序
	 */
	private Integer sort;

	/**
	 * 菜单类型
	 */
	private Integer category;

	/**
	 * 操作按钮类型
	 */
	private Integer action;

	/**
	 * 是否打开新页面
	 */
	private Integer isOpen;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 是否已删除
	 */
	@TableLogic
	private Integer isDeleted;


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		Menu other = (Menu) obj;
		if (this.getId().equals(other.getId())) {
			return true;
		}
		return false;
	}

}
