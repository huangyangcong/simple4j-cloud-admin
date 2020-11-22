package com.simple4j.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.simple4j.autoconfigure.mybatis.base.BaseEntity;
import lombok.Data;

/**
 * 岗位表实体类
 *
 * @author hyc
 */
@Data
@TableName("simple4j_post")
public class Post extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId(value = "id", type = IdType.ASSIGN_ID)
	private String id;

	/**
	 * 租户ID
	 */
	private String tenantId;
	/**
	 * 类型
	 */
	private Integer category;
	/**
	 * 岗位编号
	 */
	private String postCode;
	/**
	 * 岗位名称
	 */
	private String postName;
	/**
	 * 岗位排序
	 */
	private Integer sort;
	/**
	 * 岗位描述
	 */
	private String remark;
}
