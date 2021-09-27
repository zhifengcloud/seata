/*
 * author: wangjian (jonath@163.com)
 * Copyright 2019
 */
package com.sharding.user.service;

import com.sharding.user.entity.UserEntity;
import com.sharding.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO
 * author: wangjian (jonath@163.com)
 * date: 2021/9/9 14:53
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public boolean save(UserEntity user) {

        int count = userMapper.countByUserName(user.getUsername());
        if(count>0){
            System.out.println("用户名已经存在了");
            return false;
        }
        return userMapper.insert(user) > 0;
    }
}
