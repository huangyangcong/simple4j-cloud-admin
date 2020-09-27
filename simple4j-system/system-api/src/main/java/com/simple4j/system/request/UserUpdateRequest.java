package com.simple4j.system.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hyc
 * @version 1.0.0
 */
@Data
public class UserUpdateRequest extends UserAddRequest {


	/**
	 * 用户ID
	 */
	@ApiModelProperty(value = "用户ID")
	private Long id;

}
