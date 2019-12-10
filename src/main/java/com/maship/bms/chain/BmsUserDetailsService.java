package com.maship.bms.chain;

import com.maship.bms.model.entity.Permission;
import com.maship.bms.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Clint
 * Date: 2019-12-10 16:14
 * Description:
 */
@Component
public class BmsUserDetailsService implements UserDetailsService {
  @Resource
  private UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userService.query(username).map( user -> {
      List<Permission> permissions = userService.listPermission(user.getId());
      return new BmsUserDetails(user, permissions);
    }).orElseThrow(() -> new UsernameNotFoundException("用户名或密码错误"));
  }

  public UsernamePasswordAuthenticationToken auth(UserDetails userDetails) {
    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(authentication);
    return authentication;
  }
}
