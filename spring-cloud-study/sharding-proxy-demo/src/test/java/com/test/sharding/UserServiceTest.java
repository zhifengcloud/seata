/*
 * author: wangjian (jonath@163.com)
 * Copyright 2019
 */
package com.test.sharding;

import com.sharding.user.UserApplicaiton;
import com.sharding.user.entity.UserEntity;
import com.sharding.user.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * TODO
 * author: wangjian (jonath@163.com)
 * date: 2021/9/9 16:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplicaiton.class)
//@Slf4j
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void saveUserTest(){
        String userName ="xiaoming3";
        UserEntity user = UserEntity.builder()
                .username(userName).status(1)
                .createTime(new Date()).build();

        boolean result = userService.save(user);
        System.out.println(user);
        Assert.assertTrue(result);
    }
}
