/*
 * author: wangjian (jonath@163.com)
 * Copyright 2019
 */
package com.yzf.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 * author: wangjian (jonath@163.com)
 * date: 2021/8/2 14:43
 */
@RestController
public class TestController {

    @GetMapping("/gateway/{test}")
    public String test(@PathVariable(value = "test") String test){
        return "hello-gateway:"+test;
    }
}
