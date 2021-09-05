package com.simple4j.gen.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.io.Serializable;

/**
 * 代码生成表新增请求实体类
 *
 * @author hyc
 * @since 2020-09-19
 */
@Data
@Schema(name = "代码生成表新增请求实体类", description = "代码生成表新增请求实体类")
public class CodeAddRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 数据源主键
	 */
	@Parameter(name = "datasource_id", description = "数据源主键")
	@JsonProperty("datasource_id")
	private Long datasourceId;
	/**
	 * 服务名称
	 */
	@Parameter(name = "service_name", description = "服务名称")
	@JsonProperty("service_name")
	private String serviceName;
	/**
	 * 模块名称
	 */
	@Parameter(name = "code_name", description = "模块名称")
	@JsonProperty("code_name")
	private String codeName;
	/**
	 * 表名
	 */
	@Parameter(name = "table_name", description = "表名")
	@JsonProperty("table_name")
	private String tableName;
	/**
	 * 表前缀
	 */
	@Parameter(name = "table_prefix", description = "表前缀")
	@JsonProperty("table_prefix")
	private String tablePrefix;
	/**
	 * 主键名
	 */
	@Parameter(name = "pk_name", description = "主键名")
	@JsonProperty("pk_name")
	private String pkName;
	/**
	 * 后端包名
	 */
	@Parameter(name = "package_name", description = "后端包名")
	@JsonProperty("package_name")
	private String packageName;
	/**
	 * 基础业务模式
	 */
	@Parameter(name = "base_mode", description = "基础业务模式")
	@JsonProperty("base_mode")
	private Integer baseMode;
	/**
	 * 包装器模式
	 */
	@Parameter(name = "wrap_mode", description = "包装器模式")
	@JsonProperty("wrap_mode")
	private Integer wrapMode;
	/**
	 * 后端路径
	 */
	@Parameter(name = "api_path", description = "后端路径")
	@JsonProperty("api_path")
	private String apiPath;
	/**
	 * 前端路径
	 */
	@Parameter(name = "web_path", description = "前端路径")
	@JsonProperty("web_path")
	private String webPath;
}
