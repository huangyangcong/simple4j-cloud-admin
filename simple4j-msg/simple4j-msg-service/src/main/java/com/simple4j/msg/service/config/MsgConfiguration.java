package com.simple4j.msg.service.config;

import cn.jpush.api.JPushClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MsgConfiguration {
  @Value("#{jpush.master_secret}")
  private String masterSecret;

  @Value("#{jpush.app_key}")
  private String appKey;

  @Bean
  public JPushClient jPushClient() {
    return new JPushClient(masterSecret, appKey);
  }
}
