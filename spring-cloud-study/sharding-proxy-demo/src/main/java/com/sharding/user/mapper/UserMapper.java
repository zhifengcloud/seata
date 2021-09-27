/*
 * author: wangjian (jonath@163.com)
 * Copyright 2019
 */
package com.sharding.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sharding.user.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * TODO
 * author: wangjian (jonath@163.com)
 * date: 2021/9/9 14:52
 */
public interface UserMapper extends BaseMapper<UserEntity> {


    @Select("select count(*) from t_user where username=#{userName}")
    int countByUserName(@Param("userName") String userName);
}
