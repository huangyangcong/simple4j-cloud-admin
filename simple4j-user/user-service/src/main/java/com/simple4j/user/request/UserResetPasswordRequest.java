package com.simple4j.user.request;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserResetPasswordRequest {

	@ApiModelProperty(name = "user_ids", value = "用户编号列表")
	List<Long> userIds;

}
