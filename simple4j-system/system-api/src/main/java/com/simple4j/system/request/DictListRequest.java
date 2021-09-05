package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.io.Serializable;

/**
 * 字典表列表请求实体类
 *
 * @author hyc
 * @since 2020-08-25
 */
@Data
@Schema(name = "字典表列表请求实体类", description = "字典表列表请求实体类")
public class DictListRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@Parameter(description = "字典编号", name = "code")
	@JsonProperty("code")
	private String code;

	@Parameter(description = "字典名称", name = "dict_value")
	@JsonProperty("dict_value")
	private String dictValue;
}
