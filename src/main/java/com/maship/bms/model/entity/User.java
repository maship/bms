package com.maship.bms.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table user
 */
@Data
public class User implements Serializable {
    private Long id;

    @ApiModelProperty(value = "用户登录名")
    private String username;

    @ApiModelProperty(value = "密码")
    @JsonIgnore
    private String password;

    @ApiModelProperty(value = "头像")
    private String icon;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "备注信息")
    private String note;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "最后登录时间")
    private Date loginTime;

    @ApiModelProperty(value = "帐号启用状态: 0->启用;1->禁用")
    private Boolean disable;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table user
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

}