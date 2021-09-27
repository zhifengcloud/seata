/*
 * author: wangjian (jonath@163.com)
 * Copyright 2019
 */
package com.sharding.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * TODO
 * author: wangjian (jonath@163.com)
 * date: 2021/9/9 12:20
 */
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.sharding.user.mapper")
public class UserApplicaiton {
    public static void main(String[] args) {
        SpringApplication.run(UserApplicaiton.class, args);
    }
}
