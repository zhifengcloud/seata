/*
 * author: wangjian (jonath@163.com)
 * Copyright 2019
 */
package com.yzf.cloud.controller;

import com.yzf.cloud.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * TODO
 * author: wangjian (jonath@163.com)
 * date: 2021/7/22 14:20
 */
@RestController
public class HelloController {

    private static Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestService testService;

    @Autowired
    private RestTemplate restTemplate;



    @GetMapping("/hello")
    public String sayHello(String msg) {
        log.info("get /sayHello. consumer-msg={}",msg);
        System.out.println("consumer-sayHello:" + msg);
        return testService.sayHello(msg);
    }

    @GetMapping(value = "/echo/{str}")
    public String echo(@PathVariable String str) {
        System.out.println("echo...");
        log.info("get /echo/{}",str);
        return restTemplate.getForObject("http://nacos-provider-config/echo/" + str, String.class);
    }
}
