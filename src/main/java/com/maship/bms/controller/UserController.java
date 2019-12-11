package com.maship.bms.controller;

import com.maship.bms.common.req.LoginReq;
import com.maship.bms.common.resp.CommonResult;
import com.maship.bms.common.resp.ResultCode;
import com.maship.bms.model.entity.User;
import com.maship.bms.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Clint
 * Date: 2019-12-05 18:15
 * Description:
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

  @Resource
  private UserService userService;

  @Value("${jwt.tokenHead}")
  private String tokenHead;

  @ApiOperation(value = "用户登录", notes = "登录返回jwt token")
  @PostMapping("login")
  public CommonResult<Map<String, String>> login(@RequestBody LoginReq req) {
    String token = userService.login(req);
    Map<String, String> tokenMap = new HashMap<>();
    tokenMap.put("token", token);
    tokenMap.put("tokenHead", tokenHead);
    return CommonResult.succ(tokenMap);
  }

  @ApiOperation(value = "用户信息", notes = "查询当前登录用户信息")
  @GetMapping("info")
  public CommonResult<User> info(Principal principal) {
    Optional<User> opt = userService.query(principal.getName());
    return opt.map(CommonResult::succ).orElseGet(() -> CommonResult.fail(ResultCode.UNAUTHORIZED));
  }

}