package com.simple4j.autoconfigure.jwt.config;

import com.simple4j.autoconfigure.jwt.dynamic.DynamicFilterInvocationSecurityMetadataSource;
import com.simple4j.autoconfigure.jwt.dynamic.DynamicRequestMatcher;
import com.simple4j.autoconfigure.jwt.dynamic.DynamicSecurityService;
import com.simple4j.autoconfigure.jwt.properties.JwtProperties;
import com.simple4j.autoconfigure.jwt.security.DefaultTokenProvider;
import com.simple4j.autoconfigure.jwt.security.DefaultTokenServiceImpl;
import com.simple4j.autoconfigure.jwt.security.JwtToken;
import com.simple4j.autoconfigure.jwt.security.RedisTokenStore;
import com.simple4j.autoconfigure.jwt.security.TokenProvider;
import com.simple4j.autoconfigure.jwt.security.TokenService;
import com.simple4j.autoconfigure.jwt.security.TokenStore;
import com.simple4j.autoconfigure.jwt.security.server.JwtServerAuthenticationWebFilter;
import com.simple4j.autoconfigure.jwt.security.server.ReactiveTokenResolve;
import com.simple4j.autoconfigure.jwt.security.servlet.DefaultServletTokenResolve;
import com.simple4j.autoconfigure.jwt.security.servlet.JwtAuthenticationProvider;
import com.simple4j.autoconfigure.jwt.security.servlet.ServletTokenResolve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.JdbcOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * jwt auto configuration.
 *
 * @author hyc
 * @version 1.0.0
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(JwtProperties.class)
public class JwtAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean(TokenProvider.class)
  public TokenProvider tokenProvider(JwtProperties properties) {
    return new DefaultTokenProvider(properties.getBase64Secret());
  }

  @Bean
  @ConditionalOnMissingBean(TokenService.class)
  public TokenService tokenService(
      TokenStore tokenStore,
      TokenProvider tokenProvider,
      AuthenticationManagerBuilder authenticationManagerBuilder) {
    return new DefaultTokenServiceImpl(tokenStore, tokenProvider, authenticationManagerBuilder);
  }

  @Bean
  @ConditionalOnMissingBean(TokenStore.class)
  public TokenStore tokenStore(RedisTemplate redisTemplate, JwtProperties jwtProperties) {
    return new RedisTokenStore(jwtProperties, redisTemplate);
  }

  @Bean
  public OAuth2AuthorizedClientService oAuth2AuthorizedClientService(
      JdbcTemplate jdbcTemplate, ClientRegistrationRepository clientRegistrationRepository) {
    return new JdbcOAuth2AuthorizedClientService(jdbcTemplate, clientRegistrationRepository);
  }

  @Bean
  public GrantedAuthorityDefaults grantedAuthorityDefaults() {
    // 去除 ROLE_ 前缀
    return new GrantedAuthorityDefaults("");
  }

  @Configuration(proxyBeanMethods = false)
  @EnableWebSecurity
  // @EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
  @ConditionalOnWebApplication(type = Type.SERVLET)
  @ConditionalOnClass({SessionCreationPolicy.class})
  @AutoConfigureAfter({WebMvcAutoConfiguration.class})
  public static class ServletJwtAutoConfig extends WebSecurityConfigurerAdapter {

    @Autowired(required = false)
    private DynamicSecurityService dynamicSecurityService;

    @Lazy @Autowired private ServletTokenResolve servletTokenResolve;

    @Lazy @Autowired private DynamicRequestMatcher dynamicRequestMatcher;

    @Lazy
    @Autowired(required = false)
    private DynamicFilterInvocationSecurityMetadataSource
        dynamicFilterInvocationSecurityMetadataSource;

    @Autowired private TokenService tokenService;

    @Autowired private JdbcTemplate jdbcTemplate;

    @Value("${server.error.path:${error.path:/error}}")
    private String serverErrorPath;

    @Bean
    @ConditionalOnMissingBean(ServletTokenResolve.class)
    public ServletTokenResolve servletTokenResolve(JwtProperties jwtProperties) {
      return new DefaultServletTokenResolve(jwtProperties);
    }

    @Bean
    @ConditionalOnBean(DynamicSecurityService.class)
    public DynamicFilterInvocationSecurityMetadataSource
        dynamicFilterInvocationSecurityMetadataSource() {
      return new DynamicFilterInvocationSecurityMetadataSource(dynamicSecurityService);
    }

    @Bean
    public DynamicRequestMatcher dynamicRequestMatcher() {
      return new DynamicRequestMatcher(dynamicSecurityService, serverErrorPath);
    }

    @Override
    public void init(WebSecurity web) throws Exception {
      web.addSecurityFilterChainBuilder(
          (SecurityBuilder<DefaultSecurityFilterChain>)
              () -> new DefaultSecurityFilterChain(dynamicRequestMatcher));
      super.init(web);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
      httpSecurity
          // 禁用 CSRF
          .csrf()
          .disable()
          .addFilterBefore(
              (request, response, chain) -> {
                String token = servletTokenResolve.resolveToken((HttpServletRequest) request);
                if (!StringUtils.isEmpty(token)) {
                  SecurityContextHolder.getContext().setAuthentication(new JwtToken(token));
                }
                chain.doFilter(request, response);
              },
              UsernamePasswordAuthenticationFilter.class)
          .authenticationProvider(new JwtAuthenticationProvider(tokenService))
          // 授权异常
          .exceptionHandling()
          .disable()
          // 防止iframe 造成跨域
          .headers()
          .xssProtection()
          .and()
          .frameOptions()
          .disable()
          // 不创建会话
          .and()
          .sessionManagement()
          .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
          .and()
          .oauth2Login()
          .and()
          .oauth2Client();
      // 有动态权限配置时添加动态权限校验过滤器
      if (dynamicSecurityService != null) {
        httpSecurity
            .authorizeRequests()
            .mvcMatchers(HttpMethod.OPTIONS, "/**")
            .permitAll()
            .withObjectPostProcessor(
                new ObjectPostProcessor<FilterSecurityInterceptor>() {
                  public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
                    fsi.setSecurityMetadataSource(dynamicFilterInvocationSecurityMetadataSource);
                    return fsi;
                  }
                });
      }
    }
  }

  @Configuration(proxyBeanMethods = false)
  @EnableWebFluxSecurity
  @EnableReactiveMethodSecurity
  @ConditionalOnWebApplication(type = Type.REACTIVE)
  @ConditionalOnClass({SessionCreationPolicy.class})
  @AutoConfigureAfter({WebMvcAutoConfiguration.class})
  public static class ReactorJwtAutoConfig {
    @Lazy @Autowired private ReactiveTokenResolve reactiveTokenResolve;

    @Lazy @Autowired private TokenService tokenService;

    @Autowired(required = false)
    private DynamicSecurityService dynamicSecurityService;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {

      http
          // 禁用 CSRF
          .csrf()
          .disable()
          // 防止iframe 造成跨域
          .headers()
          .frameOptions()
          .disable()
          .and()
          .authorizeExchange();
      http.addFilterBefore(
          new JwtServerAuthenticationWebFilter(reactiveTokenResolve, tokenService),
          SecurityWebFiltersOrder.AUTHENTICATION);

      // 有动态权限配置时添加动态权限校验过滤器
      if (dynamicSecurityService != null) {
        // DynamicReactorSecurityFilter dynamicSecurityFilter =
        //	new DynamicReactorSecurityFilter(dynamicSecurityService, );
        // http.addFilterBefore(dynamicSecurityFilter, SecurityWebFiltersOrder.AUTHENTICATION);
      }
      return http.build();
    }
  }
}
