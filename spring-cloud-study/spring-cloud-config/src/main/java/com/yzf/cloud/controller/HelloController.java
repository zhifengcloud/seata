/*
 * author: wangjian (jonath@163.com)
 * Copyright 2019
 */
package com.yzf.cloud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 * author: wangjian (jonath@163.com)
 * date: 2021/7/21 20:12
 */
@RestController
public class HelloController {

    private static Logger log = LoggerFactory.getLogger(HelloController.class);

    @Value(value = "${config.info:Local Hello World}")
    private String info;


    //    @Value("${share.config1}")
    private String shareConfig1;

//    @Value("${share.config2}")
//    private String shareConfig2;

    @GetMapping("/test")
    public String get(String msg) {

        log.info("get /test. provider-msg={}", msg);

        System.out.println("hello:" + msg);
        return "spring.cloudy: " + msg;
    }

    @GetMapping("/config")
    public String getConfig() {
        System.out.println("config：" + info);
        log.info("get /config. info:{}", info);
        return "hello nacos-config " + info;
    }

    @GetMapping("/config1")
    public String get2() {
        return "share: " + shareConfig1;
    }


    @GetMapping(value = "/echo/{string}")
    public String echo(@PathVariable String string) {
        log.info("get /echo/{}", string);
        return "Hello Nacos Discovery " + string;
    }
}
