package com.maship.bms.chain;

import com.alibaba.druid.support.json.JSONUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maship.bms.common.resp.CommonResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Clint
 * Date: 2019-12-10 15:48
 * Description: 自定义未登陆授权
 */
@Component
public class RestfulAuthenticationEntryPoint implements AuthenticationEntryPoint {
  @Resource
  private ObjectMapper objectMapper;

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
    response.setCharacterEncoding("UTF-8");
    response.setContentType("application/json");
    response.getWriter().println(objectMapper.writeValueAsString(CommonResult.unauthorized(e.getMessage())));
    response.getWriter().flush();
  }
}
