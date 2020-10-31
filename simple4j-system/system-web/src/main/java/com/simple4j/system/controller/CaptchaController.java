package com.simple4j.system.controller;

import com.simple4j.system.response.CaptchaResponse;
import com.simple4j.system.service.ICaptchaService;
import com.simple4j.web.bean.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

@RestController
@RequestMapping("/captcha")
@Api(tags = "验证码")
@RequiredArgsConstructor
public class CaptchaController {

  private final ICaptchaService iCaptchaService;

  @PostMapping("")
  @ApiOperation(value = "获取验证码")
  @PermitAll()
  public ApiResponse<CaptchaResponse> captcha() {
    return ApiResponse.ok(iCaptchaService.captcha());
  }
}
