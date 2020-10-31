package com.simple4j.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.simple4j.autoconfigure.mybatis.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 租户
 *
 * @author hyc
 */
@Data
@TableName("simple4j_tenant")
@EqualsAndHashCode(callSuper = true)
public class Tenant extends BaseEntity {

  private static final long serialVersionUID = 1L;

  /** 主键id */
  @TableId(value = "id", type = IdType.ASSIGN_ID)
  private Long id;

  /** 租户ID */
  private String tenantId;
  /** 租户名称 */
  private String tenantName;
  /** 联系人 */
  private String linkman;
  /** 联系电话 */
  private String contactNumber;
  /** 联系地址 */
  private String address;
}
