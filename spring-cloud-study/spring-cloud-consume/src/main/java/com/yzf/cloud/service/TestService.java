/*
 * author: wangjian (jonath@163.com)
 * Copyright 2019
 */
package com.yzf.cloud.service;

import com.yzf.cloud.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * TODO
 * author: wangjian (jonath@163.com)
 * date: 2021/7/21 20:28
 */
@Component
// value指定服务名   fallback指定失败回调类
@FeignClient(name = "nacos-provider-config",fallback = TestFalilBackService.class, configuration = FeignConfig.class)
public interface TestService {

    @GetMapping("/test")
//    @GetMapping("/nacos-provider-config/test")
    String sayHello(@RequestParam(value = "msg")  String msg);
}
