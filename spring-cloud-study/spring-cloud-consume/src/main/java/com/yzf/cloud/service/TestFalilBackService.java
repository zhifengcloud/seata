/*
 * author: wangjian (jonath@163.com)
 * Copyright 2019
 */
package com.yzf.cloud.service;

import org.springframework.stereotype.Component;

/**
 * TODO
 * author: wangjian (jonath@163.com)
 * date: 2021/7/21 20:39
 */
@Component
public class TestFalilBackService implements TestService{
    @Override
    public String sayHello(String msg) {
        return "feign failed!";
    }
}
