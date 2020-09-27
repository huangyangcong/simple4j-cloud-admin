package com.simple4j.system.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.simple4j.dao.base.BaseEntity;
import lombok.Data;

/**
 * Dept对象
 *
 * @author Chill
 */
@Data
@TableName("simple4j_dept")
public class Dept  extends BaseEntity {

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
	 * 部门名
	 */
	private String deptName;

	/**
	 * 部门全称
	 */
	private String fullName;

	/**
	 * 排序
	 */
	private Integer sort;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 是否已删除
	 */
	@TableLogic
	private Integer isDeleted;


}
