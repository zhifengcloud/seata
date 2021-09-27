/*
 * author: wangjian (jonath@163.com)
 * Copyright 2019
 */
package com.sharding.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * TODO
 * author: wangjian (jonath@163.com)
 * date: 2021/9/9 16:16
 */
@Data
@TableName("t_user_score")
public class UserScore {

    private Long uid;

    private int score;

    private Date createTime;
}
