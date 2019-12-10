package com.maship.bms.service;

import com.maship.bms.model.entity.Permission;
import com.maship.bms.model.entity.User;
import com.maship.bms.common.req.LoginReq;

import java.util.List;
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
   * 登录 成功返回token
   * @param req
   * @return
   */
  String login(LoginReq req);

  /**
   * 查询用户权限
   * @param userId
   * @return
   */
  List<Permission> listPermission(Long userId);
}
