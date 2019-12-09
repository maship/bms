package com.maship.bms.common.resp;

/**
 * Created by Clint
 * Date: 2019-12-09 12:16
 * Description:
 */
public enum ResultCode implements ICode {
  SUCCESS(0, ""),
  VALIDATE_FATAL(1000, "参数校验失败"),
  UNAUTHORIZED(4000, "未登录或登录已失效"),
  FORBIDDEN(4003, "没有相关权限"),
  SERVER_FATAL(5000, "系统错误");

  private int code;
  private String msg;

  private ResultCode(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }


  @Override
  public int getCode() {
    return code;
  }

  @Override
  public String getMsg() {
    return msg;
  }
}
