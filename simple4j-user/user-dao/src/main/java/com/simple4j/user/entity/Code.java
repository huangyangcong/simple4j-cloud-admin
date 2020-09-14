package com.simple4j.user.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 实体类
 *
 * @author Chill
 */
@Data
@TableName("simple4j_code")
public class Code implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.ASSIGN_ID)
	private Long id;

	/**
	 * 数据源主键
	 */
	private Long datasourceId;

	/**
	 * 模块名称
	 */
	private String serviceName;

	/**
	 * 模块名称
	 */
	private String codeName;

	/**
	 * 表名
	 */
	private String tableName;

	/**
	 * 实体名
	 */
	private String tablePrefix;

	/**
	 * 主键名
	 */
	private String pkName;

	/**
	 * 基础业务模式
	 */
	private Integer baseMode;

	/**
	 * 包装器模式
	 */
	private Integer wrapMode;

	/**
	 * 后端包名
	 */
	private String packageName;

	/**
	 * 后端路径
	 */
	private String apiPath;

	/**
	 * 前端路径
	 */
	private String webPath;

	/**
	 * 是否已删除
	 */
	@TableLogic
	private Integer isDeleted;


}
