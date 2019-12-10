package com.maship.bms.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Permission implements Serializable {
    private Long id;
    private Long pid;
    private String name;
    private String value;
    private String icon;
    private Integer type;
    private String uri;
    private Boolean disable;
    private Date createTime;
    private Integer sort;

    private static final long serialVersionUID = 1L;

}