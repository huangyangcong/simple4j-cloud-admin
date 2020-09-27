package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 字典表列表请求实体类
 *
 * @author hyc
 * @since 2020-08-25
 */
@Data
@ApiModel(value = "字典表列表请求实体类", description = "字典表列表请求实体类")
public class DictListRequest implements Serializable {

	private static final long serialVersionUID = 1L;


	@ApiModelProperty(value = "字典编号", name = "code")
	@JsonProperty("code")
	private String code;

	@ApiModelProperty(value = "字典名称", name = "dict_value")
	@JsonProperty("dict_value")
	private String dictValue;

}
