package com.maship.bms.service.impl;

import com.maship.bms.model.dao.UserDao;
import com.maship.bms.model.entity.User;
import com.maship.bms.common.req.LoginReq;
import com.maship.bms.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

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
  private UserDao userDao;

  @Override
  public Optional<User> query(String username) {
    User user = userDao.query(username);
    return Optional.ofNullable(user);
  }

  @Override
  public boolean login(final LoginReq req) {
    Optional<User> optionalUser = query(req.getUsername());
    return optionalUser.map(u ->
        // 数据库中该用户和请求体中的密码匹配
        passwordEncoder.matches(req.getPassword(), u.getPassword())
    ).orElse(false);
  }
}
