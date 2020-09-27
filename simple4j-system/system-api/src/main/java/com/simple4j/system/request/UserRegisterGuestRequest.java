package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hyc
 * @version 1.0.0
 */
@Data
public class UserRegisterGuestRequest extends UserAddRequest{

	@ApiModelProperty(name = "oauth_id", value = "第三方id")
	@JsonProperty("oauth_id")
	private Long oauthId;
}
