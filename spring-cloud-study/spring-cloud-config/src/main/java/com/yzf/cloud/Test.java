/*
 * author: wangjian (jonath@163.com)
 * Copyright 2019
 */
package com.yzf.cloud;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * TODO
 * author: wangjian (jonath@163.com)
 * date: 2021/7/22 11:00
 */
public class Test {

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encoderPwd = bCryptPasswordEncoder.encode("123456");

        boolean result = bCryptPasswordEncoder.upgradeEncoding("123456");
        System.out.println(encoderPwd);
        System.out.println(result);
    }
}
