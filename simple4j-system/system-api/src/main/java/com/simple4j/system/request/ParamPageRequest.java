package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.io.Serializable;

/**
 * 参数表分页请求实体类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@Schema(name = "参数表分页请求实体类", description = "参数表分页请求实体类")
public class ParamPageRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 页码
	 */
	@Parameter(name = "page_no", description = "页码")
	@JsonProperty("page_no")
	private int pageNo;

	/**
	 * 分页数
	 */
	@Parameter(name = "pageSize", description = "分页数")
	@JsonProperty("page_size")
	private int pageSize;

	@Parameter(name = "param_name", description = "参数名称")
	@JsonProperty("param_name")
	private String paramName;

	@Parameter(name = "param_key", description = "参数键名")
	@JsonProperty("param_key")
	private String paramKey;

	@Parameter(name = "param_value", description = "参数键值")
	@JsonProperty("param_value")
	private String paramValue;
}
