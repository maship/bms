package com.maship.bms.common.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Clint
 * Date: 2019-12-09 12:02
 * Description: 统一返回体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {
  private int code;
  private String msg;
  private T data;

  public static <T> CommonResult<T> succ(T data) {
    return new CommonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), data);
  }

  public static <T> CommonResult<T> fail(ICode iCode) {
    return new CommonResult<T>(iCode.getCode(), iCode.getMsg(), null);
  }

  public static <T> CommonResult<T> unauthorized(String msg) {
    return new CommonResult<T>(ResultCode.UNAUTHORIZED.getCode(), msg, null);
  }

  public static <T> CommonResult<T> forbidden(String msg) {
    return new CommonResult<T>(ResultCode.FORBIDDEN.getCode(), msg, null);
  }

}
