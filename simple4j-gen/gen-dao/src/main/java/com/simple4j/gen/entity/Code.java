package com.simple4j.gen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.simple4j.dao.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 代码生成表实体类
 *
 * @author hyc
 * @since 2020-09-19
 */
@Data
@TableName("simple4j_code")
@EqualsAndHashCode(callSuper = true)
public class Code extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.NONE)
	private Long id;
	/**
	 * 数据源主键
	 */
	private Long datasourceId;
	/**
	 * 服务名称
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
	 * 表前缀
	 */
	private String tablePrefix;
	/**
	 * 主键名
	 */
	private String pkName;
	/**
	 * 后端包名
	 */
	private String packageName;
	/**
	 * 基础业务模式
	 */
	private Integer baseMode;
	/**
	 * 包装器模式
	 */
	private Integer wrapMode;
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
