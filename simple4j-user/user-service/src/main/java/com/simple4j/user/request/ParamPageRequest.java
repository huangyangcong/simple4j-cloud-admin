package com.simple4j.user.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 参数表分页请求实体类
 *
 * @author Blade
 * @since 2020-08-26
 */
@Data
@ApiModel(value = "参数表分页请求实体类", description = "参数表分页请求实体类")
public class ParamPageRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 页码
	 */
	@ApiModelProperty(name = "page_no", value = "页码")
	@JsonProperty("page_no")
	private int pageNo;

	/**
	 * 分页数
	 */
	@ApiModelProperty(name = "pageSize", value = "分页数")
	@JsonProperty("page_size")
	private int pageSize;

	@ApiModelProperty(name = "param_name", value = "参数名称")
	@JsonProperty("param_name")
	private String paramName;

	@ApiModelProperty(name = "param_key", value = "参数键名")
	@JsonProperty("param_key")
	private String paramKey;

	@ApiModelProperty(name = "param_value", value = "参数键值")
	@JsonProperty("param_value")
	private String paramValue;
}
