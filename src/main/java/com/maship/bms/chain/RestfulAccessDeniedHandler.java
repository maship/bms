package com.maship.bms.chain;

import com.alibaba.druid.support.json.JSONUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maship.bms.common.resp.CommonResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Clint
 * Date: 2019-12-10 15:51
 * Description: 访问api接口无权限
 */
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
  @Resource
  private ObjectMapper objectMapper;

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
    response.setCharacterEncoding("UTF-8");
    response.setContentType("application/json");
    response.getWriter().println(objectMapper.writeValueAsString(CommonResult.forbidden(e.getMessage())));
    response.getWriter().flush();
  }
}
