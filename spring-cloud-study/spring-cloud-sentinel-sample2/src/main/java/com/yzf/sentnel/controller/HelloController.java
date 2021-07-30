/*
 * author: wangjian (jonath@163.com)
 * Copyright 2019
 */
package com.yzf.sentnel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 * author: wangjian (jonath@163.com)
 * date: 2021/7/23 11:46
 */
@RestController
public class HelloController {

    @SentinelResource(value = "hello", blockHandler = "blockHandlerHello")
    @GetMapping("/say")
    public String hello(){
        return "hello ,Sentinel-流控";
    }

    public String blockHandlerHello(BlockException e){
        return "你被限流了";
    }
}
