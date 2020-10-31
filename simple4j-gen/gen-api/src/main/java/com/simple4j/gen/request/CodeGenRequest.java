package com.simple4j.gen.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

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
	private String system = "simple4j" ;

	@ApiModelProperty("maven中groupId")
	@JsonProperty("group_id")
	private String groupId;

	@ApiModelProperty("项目名称")
	@JsonProperty("project_name")
	private String projectName;

	@ApiModelProperty("模块名称")
	@JsonProperty("module_name")
	private String moduleName;
}
