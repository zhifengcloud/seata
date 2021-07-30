/*
 * author: wangjian (jonath@163.com)
 * Copyright 2019
 */
package com.yzf.sentnel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * TODO
 * author: wangjian (jonath@163.com)
 * date: 2021/7/23 11:45
 */
@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudSentinelApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudSentinelApplication.class, args);
    }

}
