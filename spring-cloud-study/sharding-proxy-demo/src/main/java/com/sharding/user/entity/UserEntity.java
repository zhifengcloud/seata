/*
 * author: wangjian (jonath@163.com)
 * Copyright 2019
 */
package com.sharding.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * TODO
 * author: wangjian (jonath@163.com)
 * date: 2021/9/9 12:22
 */
@Data
@Builder
@TableName("t_user")
public class UserEntity {

    private Long id;

    private String username;

    private Integer status;

    private Date createTime;
}
