package com.maship.bms.chain;

import com.maship.bms.config.JwtConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Clint
 * Date: 2019-12-10 16:09
 * Description:
 */
@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
  @Resource
  private BmsUserDetailsService bmsUserDetailsService;
  @Resource
  private JwtTokenUtil jwtTokenUtil;

  @Resource
  private JwtConfig jwtConfig;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
    String authHeader = request.getHeader(jwtConfig.getTokenHeader());
    if (authHeader != null && authHeader.startsWith(jwtConfig.getTokenHead())) {
      String authToken = authHeader.substring(jwtConfig.getTokenHead().length());// The part after "Bearer "
      String username = jwtTokenUtil.getUserNameFromToken(authToken);

      log.info("checking username:{}", username);
      if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = bmsUserDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.validateToken(authToken, userDetails)) {
          UsernamePasswordAuthenticationToken authentication = bmsUserDetailsService.auth(userDetails);

          authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          log.info("authenticated user:{}", username);
        }
      }
    }
    chain.doFilter(request, response);
  }
}
