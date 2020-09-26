package com.simple4j.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.simple4j.dao.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 数据源配置表实体类
 *
 * @author Chill
 */
@Data
@TableName("simple4j_datasource")
@EqualsAndHashCode(callSuper = true)
public class Datasource extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.ASSIGN_ID)
	private Long id;

	/**
	 * 名称
	 */
	private String name;
	/**
	 * 驱动类
	 */
	private String driverClass;
	/**
	 * 连接地址
	 */
	private String url;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 备注
	 */
	private String remark;


}
