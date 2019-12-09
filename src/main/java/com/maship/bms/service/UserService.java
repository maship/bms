package com.maship.bms.service;

import com.maship.bms.model.entity.User;
import com.maship.bms.common.req.LoginReq;

import java.util.Optional;

/**
 * Created by Clint
 * Date: 2019-12-09 14:35
 * Description:
 */
public interface UserService {

  /**
   * 根据username查询用户
   * @param username
   * @return
   */
  Optional<User> query(String username);

  /**
   * 登录
   * @param req
   * @return
   */
  boolean login(LoginReq req);
}
