package com.maship.bms.common.req;

import lombok.Data;

/**
 * Created by Clint
 * Date: 2019-12-09 11:51
 * Description: 登录请求体 用户名密码
 */
@Data
public class LoginReq {
  private String username;
  private String password;
}
