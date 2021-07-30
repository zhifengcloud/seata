/*
 * author: wangjian (jonath@163.com)
 * Copyright 2019
 */
package com.work.order.controller;

import com.work.order.feign.AccountFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 * author: wangjian (jonath@163.com)
 * date: 2021/7/27 17:16
 */
@RestController
public class TestController {
    @Autowired
    private AccountFeignClient accountFeignClient;

    @GetMapping("test2")
    public String test(){
        return "test:"+accountFeignClient;
    }
}
