package com.simple4j.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.simple4j.autoconfigure.mybatis.base.BaseEntity;
import lombok.Data;

/**
 * 角色实体类
 *
 * @author hyc
 */
@Data
@TableName("simple4j_role")
public class Role extends BaseEntity {

  private static final long serialVersionUID = 1L;

  /** 主键 */
  @TableId(value = "id", type = IdType.ASSIGN_ID)
  private String id;

  /** 租户ID */
  private String tenantId;

  /** 父主键 */
  private Long parentId;

  /** 角色名 */
  private String roleName;

  /** 排序 */
  private Integer sort;

  /** 角色别名 */
  private String roleAlias;

  /** 是否已删除 */
  @TableLogic private Integer isDeleted;
}
