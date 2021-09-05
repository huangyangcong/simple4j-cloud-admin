package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

/**
 * @author hyc
 * @version 1.0.0
 */
@Data
public class UserRegisterGuestRequest extends UserAddRequest {

	@Parameter(name = "oauth_id", description = "第三方id")
	@JsonProperty("oauth_id")
	private String oauthId;
}
