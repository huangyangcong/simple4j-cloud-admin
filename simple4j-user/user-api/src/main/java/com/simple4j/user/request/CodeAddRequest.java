package com.simple4j.user.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 代码生成表新增请求实体类
 *
 * @author Blade
 * @since 2020-09-15
 */
	@Data
	@ApiModel(value = "代码生成表添新增请求实体类", description = "代码生成表添新增请求实体类")
public class CodeAddRequest implements Serializable {

	private static final long serialVersionUID = 1L;

					/**
				 * 数据源主键
				 */
				@ApiModelProperty(name = "datasource_id", value = "数据源主键")
				@JsonProperty("datasource_id")
		private Long datasourceId;
				/**
				 * 服务名称
				 */
				@ApiModelProperty(name = "service_name", value = "服务名称")
				@JsonProperty("service_name")
		private String serviceName;
				/**
				 * 模块名称
				 */
				@ApiModelProperty(name = "code_name", value = "模块名称")
				@JsonProperty("code_name")
		private String codeName;
				/**
				 * 表名
				 */
				@ApiModelProperty(name = "table_name", value = "表名")
				@JsonProperty("table_name")
		private String tableName;
				/**
				 * 表前缀
				 */
				@ApiModelProperty(name = "table_prefix", value = "表前缀")
				@JsonProperty("table_prefix")
		private String tablePrefix;
				/**
				 * 主键名
				 */
				@ApiModelProperty(name = "pk_name", value = "主键名")
				@JsonProperty("pk_name")
		private String pkName;
				/**
				 * 后端包名
				 */
				@ApiModelProperty(name = "package_name", value = "后端包名")
				@JsonProperty("package_name")
		private String packageName;
				/**
				 * 基础业务模式
				 */
				@ApiModelProperty(name = "base_mode", value = "基础业务模式")
				@JsonProperty("base_mode")
		private Integer baseMode;
				/**
				 * 包装器模式
				 */
				@ApiModelProperty(name = "wrap_mode", value = "包装器模式")
				@JsonProperty("wrap_mode")
		private Integer wrapMode;
				/**
				 * 后端路径
				 */
				@ApiModelProperty(name = "api_path", value = "后端路径")
				@JsonProperty("api_path")
		private String apiPath;
				/**
				 * 前端路径
				 */
				@ApiModelProperty(name = "web_path", value = "前端路径")
				@JsonProperty("web_path")
		private String webPath;
	

}
