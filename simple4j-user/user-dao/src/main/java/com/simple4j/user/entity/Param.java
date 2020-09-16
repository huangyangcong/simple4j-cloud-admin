package com.simple4j.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.simple4j.user.base.BaseEntity;

/**
 * 参数实体类
 *
 * @author Chill
 */
@Data
@TableName("simple4j_param")
@EqualsAndHashCode(callSuper = true)
public class Param extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId(value = "id", type = IdType.ASSIGN_ID)
	private Long id;

	/**
	 * 参数名
	 */
	private String paramName;

	/**
	 * 参数键
	 */
	private String paramKey;

	/**
	 * 参数值
	 */
	private String paramValue;

	/**
	 * 备注
	 */
	private String remark;


}
