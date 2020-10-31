package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserLoginRequest {

  @ApiModelProperty(name = "username", value = "用户名")
  @JsonProperty("username")
  private String username;

  @ApiModelProperty(name = "password", value = "用户部门列表")
  @JsonProperty("password")
  private String password;

  @ApiModelProperty(name = "captcha_key", value = "验证码key")
  @JsonProperty("captcha_key")
  private String captchaKey;

  @ApiModelProperty(name = "captcha_code", value = "验证码")
  @JsonProperty("captcha_code")
  private String captchaCode;
}
