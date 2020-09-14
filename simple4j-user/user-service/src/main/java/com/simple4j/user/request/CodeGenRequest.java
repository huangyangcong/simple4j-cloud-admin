package com.simple4j.user.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hyc
 * @version 1.0.0
 */
@Data
public class CodeGenRequest {

	@NotNull
	@ApiModelProperty("编码详情编号")
	private List<String> ids;

	@ApiModelProperty("所属系统")
	private String system = "simple4j";
}
