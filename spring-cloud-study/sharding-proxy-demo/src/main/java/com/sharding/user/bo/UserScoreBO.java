/*
 * author: wangjian (jonath@163.com)
 * Copyright 2019
 */
package com.sharding.user.bo;

import lombok.Data;

import java.util.Date;

/**
 * TODO
 * author: wangjian (jonath@163.com)
 * date: 2021/9/9 16:30
 */
@Data
public class UserScoreBO {

    private Long uid;

    private Integer score;

    private Date createTime;
}
