package com.simple4j.system.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class UserResetPasswordRequest {

	@ApiModelProperty(name = "user_ids", value = "用户编号列表")
	List<Long> userIds;

}
