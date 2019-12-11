package com.maship.bms.service.impl;

import com.maship.bms.chain.BmsUserDetailsService;
import com.maship.bms.chain.JwtTokenUtil;
import com.maship.bms.model.dao.UserDao;
import com.maship.bms.model.entity.Permission;
import com.maship.bms.model.entity.User;
import com.maship.bms.common.req.LoginReq;
import com.maship.bms.service.UserService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Clint
 * Date: 2019-12-09 16:53
 * Description:
 */
@Service
public class UserServiceImpl implements UserService {

  @Resource
  private PasswordEncoder passwordEncoder;
  @Resource
  private BmsUserDetailsService bmsUserDetailsService;
  @Resource
  private JwtTokenUtil jwtTokenUtil;
  @Resource
  private UserDao userDao;

  @Override
  public Optional<User> query(String username) {
    User user = userDao.query(username);
    return Optional.ofNullable(user);
  }

  @Override
  public String login(final LoginReq req) {
    UserDetails userDetails = bmsUserDetailsService.loadUserByUsername(req.getUsername());
    // 数据库中该用户和请求体中的密码匹配
    if (passwordEncoder.matches(req.getPassword(), userDetails.getPassword())) {
      bmsUserDetailsService.auth(userDetails);
      return jwtTokenUtil.generateToken(userDetails);
    } else {
      throw new BadCredentialsException("密码不正确");
    }
  }

  @Override
  public List<Permission> listPermission(Long userId) {
    // 去重
    return userDao.listPermission(userId).stream().collect(
        Collectors.collectingAndThen(
            Collectors.toCollection(() ->
                new TreeSet<>(Comparator.comparing(Permission::getId))
            ),
            ArrayList::new
        )
    );
  }
}
