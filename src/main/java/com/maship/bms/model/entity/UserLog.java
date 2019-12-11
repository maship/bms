package com.maship.bms.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table user_log
 */
@Data
public class UserLog implements Serializable {
    private Long id;

    private Long userId;

    private Date createTime;

    private String ip;

    private String address;

    @ApiModelProperty(value = "浏览器登录类型")
    private String userAgent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table user_log
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

}