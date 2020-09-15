package com.simple4j.user.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserDeptGrantRequest {

	@ApiModelProperty(name = "user_ids", value = "用户编号列表")
	@JsonProperty("user_ids")
	List<Long> userIds;

	@ApiModelProperty(name = "dept_ids", value = "用户部门列表")
	@JsonProperty("dept_ids")
	List<Long> deptIds;

}
