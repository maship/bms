package com.maship.bms.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Clint
 * Date: 2019-12-19 10:01
 * Description:
 */
@Component
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtConfig {
  /**
   * JWT存储的请求头
   */
  private String tokenHeader;
  /**
   * JWT加解密使用的密钥
   */
  private String secret;
  /**
   * JWT的超期限时间(60*60*24)
   */
  private Long expiration;
  /**
   * JWT负载中拿到开头
   */
  private String tokenHead;

}
