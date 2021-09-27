/*
 * author: wangjian (jonath@163.com)
 * Copyright 2019
 */
package com.sharding.user.service;

import com.sharding.user.entity.UserScore;
import com.sharding.user.mapper.UserScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO
 * author: wangjian (jonath@163.com)
 * date: 2021/9/9 16:27
 */
@Service
public class UserScoreService {

    @Autowired
    private UserScoreMapper userScoreMapper;

    public boolean save(UserScore userScore) {
        return userScoreMapper.insert(userScore) > 0;
    }
}
