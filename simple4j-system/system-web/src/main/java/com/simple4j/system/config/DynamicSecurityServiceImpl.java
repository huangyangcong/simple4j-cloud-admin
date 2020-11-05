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
        .mvcMatchers(HttpMethod.POST, "/user/api/v1/info").permitAll()
        .mvcMatchers(HttpMethod.GET, "/login/oauth2/code/{registrationId}").permitAll()
        .mvcMatchers(HttpMethod.GET, "/client/test").permitAll()
        .mvcMatchers(HttpMethod.GET, "/user/api/v1/login2").permitAll()
        .antMatchers(HttpMethod.GET, "/login/oauth2/code/**").permitAll()
        .antMatchers(HttpMethod.GET, "/authorize/oauth2/code/**").permitAll()
        .antMatchers(HttpMethod.GET, "/authorize/oauth2/code/**").permitAll()
        .anyRequest()
        .authenticated();
  }

  @Override
  public void ignoreUrls(
      IgnoreAbstractRequestMatcherRegistry ignoreAbstractRequestMatcherRegistry) {
    ignoreAbstractRequestMatcherRegistry.mvcMatchers("/aaaa");
    ignoreAbstractRequestMatcherRegistry.mvcMatchers("/user/api/v1/login");
  }
}
