package com.simple4j.gen.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * 数据源配置表删除请求实体类
 *
 * @author hyc
 * @since 2020-09-19
 */
@Data
@Schema(name = "数据源配置表删除请求实体类", description = "数据源配置表删除请求实体类")
public class DatasourceRemoveRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 数据源配置表删除编号列表
	 */
	@Parameter(description = "数据源配置表删除编号列表", name = "ids")
	@JsonProperty("ids")
	@NotEmpty(message = "数据源配置表编号标号不能为空")
	private List<String> ids;
}
