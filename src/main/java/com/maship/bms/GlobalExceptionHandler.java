package com.maship.bms;

import com.maship.bms.common.exception.BmsException;
import com.maship.bms.common.resp.CommonResult;
import com.maship.bms.common.resp.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by Clint
 * Date: 2019-12-09 17:54
 * Description: 全局异常处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(value = { BmsException.class })
  public CommonResult<Object> defaultHandler(Exception e) {
    log.error("[BMS]业务异常", e);
    return CommonResult.fail(ResultCode.SERVER_FATAL);
  }
}
