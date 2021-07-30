/*
 * author: wangjian (jonath@163.com)
 * Copyright 2019
 */
package com.yzf.cloud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * TODO
 * author: wangjian (jonath@163.com)
 * date: 2021/7/21 20:27
 */
@RestController
public class TestController {

    private static Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/test")
    public String test() {
        log.info("get /test. ");
        System.out.println("consumer-test:" );
        return "consumer-test";
    }

    @GetMapping("/ech/hello")
    public String hello2() {
        System.out.println(restTemplate);
        log.info("ech/hello. ");
        System.out.println("consumer-test:" );
        return "ech/hello";
    }

}
