package com.simple4j.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.simple4j.user.base.BaseEntity;

/**
 * 租户
 *
 * @author Chill
 */
@Data
@TableName("simple4j_tenant")
@EqualsAndHashCode(callSuper = true)
public class Tenant extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId(value = "id", type = IdType.ASSIGN_ID)
	private Long id;

	/**
	 * 租户ID
	 */
	private String tenantId;
	/**
	 * 租户名称
	 */
	private String tenantName;
	/**
	 * 联系人
	 */
	private String linkman;
	/**
	 * 联系电话
	 */
	private String contactNumber;
	/**
	 * 联系地址
	 */
	private String address;


}
