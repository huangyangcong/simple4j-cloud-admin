package com.simple4j.gen.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Parameter;
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
	@Parameter(description = "编码详情编号")
	private List<String> ids;

	@Parameter(description = "所属系统")
	private String system = "simple4j";

	@Parameter(description = "maven中groupId")
	@JsonProperty("group_id")
	private String groupId;

	@Parameter(description = "项目名称")
	@JsonProperty("project_name")
	private String projectName;

	@Parameter(description = "模块名称")
	@JsonProperty("module_name")
	private String moduleName;
}
