package com.simple4j.system.request;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.util.List;

@Data
public class UserResetPasswordRequest {

	@Parameter(name = "user_ids", description = "用户编号列表")
	List<Long> userIds;
}
