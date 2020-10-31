package com.simple4j.system.config;

import com.simple4j.autoconfigure.jwt.dynamic.DynamicSecurityService;
import com.simple4j.autoconfigure.jwt.dynamic.IgnoreAbstractRequestMatcherRegistry;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.stereotype.Component;

/** @author hyc */
@Component
public class DynamicSecurityServiceImpl implements DynamicSecurityService {

  @Override
  public void loadDataSource(
      ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry,
      DefaultWebSecurityExpressionHandler expressionHandler) {
    registry
        .mvcMatchers(HttpMethod.POST, "/user/api/v1/info")
        .permitAll()
        .anyRequest()
        .authenticated();
  }

  @Override
  public void ignoreUrls(
      IgnoreAbstractRequestMatcherRegistry ignoreAbstractRequestMatcherRegistry) {
    ignoreAbstractRequestMatcherRegistry.mvcMatchers("/aaaa");
  }
}
