package com.maship.bms;

import com.maship.bms.chain.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.Resource;

/**
 * Created by Clint
 * Date: 2019-12-09 17:00
 * Description:
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Resource
  private BmsUserDetailsService bmsUserDetailsService;

  @Resource
  private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
  @Resource
  private RestfulAuthenticationEntryPoint restfulAuthenticationEntryPoint;
  @Resource
  private RestfulAccessDeniedHandler restfulAccessDeniedHandler;

  private static final String[] AUTH_WHITELIST = {
      // login
      "/user/login",
      // -- swagger ui
      "/swagger-resources/**",
      "/swagger-ui.html",
      "/v2/api-docs",
      "/webjars/**"
  };

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // 禁用跨域请求限制
    http.csrf().disable();
    // 登录鉴权
    http.authorizeRequests()
        .antMatchers(AUTH_WHITELIST).permitAll()
        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()  // 跨域请求会先进行一次options请求
        .anyRequest().authenticated();
    // JWT filter
    http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    // 自定义未授权/未登录
    http.exceptionHandling()
        .accessDeniedHandler(restfulAccessDeniedHandler)
        .authenticationEntryPoint(restfulAuthenticationEntryPoint);
    // session生成策略 有需要才生成
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(bmsUserDetailsService)
        .passwordEncoder(passwordEncoder());
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * 允许跨域调用的过滤器
   */
  @Bean
  public CorsFilter corsFilter() {
    CorsConfiguration config = new CorsConfiguration();
    //允许所有域名进行跨域调用
    config.addAllowedOrigin("*");
    //允许跨越发送cookie
    config.setAllowCredentials(true);
    //放行全部原始头信息
    config.addAllowedHeader("*");
    //允许所有请求方法跨域调用
    config.addAllowedMethod("*");
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
  }

}
