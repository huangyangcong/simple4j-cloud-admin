package com.simple4j.user.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 角色实体类
 *
 * @author Chill
 */
@Data
@TableName("simple4j_role")
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.ASSIGN_ID)
	private Long id;

	/**
	 * 租户ID
	 */
	private String tenantId;

	/**
	 * 父主键
	 */
	private Long parentId;

	/**
	 * 角色名
	 */
	private String roleName;

	/**
	 * 排序
	 */
	private Integer sort;

	/**
	 * 角色别名
	 */
	private String roleAlias;

	/**
	 * 是否已删除
	 */
	@TableLogic
	private Integer isDeleted;


}
