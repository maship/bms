package com.maship.bms.controller;

import com.maship.bms.common.req.LoginReq;
import com.maship.bms.common.resp.CommonResult;
import com.maship.bms.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Clint
 * Date: 2019-12-05 18:15
 * Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

  @Resource
  private UserService userService;

  @PostMapping("login")
  public CommonResult<Boolean> login(@RequestBody LoginReq req) {
    boolean f = userService.login(req);
    return CommonResult.succ(f);
  }
}