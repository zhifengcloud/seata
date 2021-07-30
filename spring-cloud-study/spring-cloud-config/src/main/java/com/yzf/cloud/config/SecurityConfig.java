/*
 * author: wangjian (jonath@163.com)
 * Copyright 2019
 */
package com.yzf.cloud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * TODO
 * author: wangjian (jonath@163.com)
 * date: 2021/7/22 15:08
 */


@Configuration
@EnableWebSecurity
//@EnableAutoConfiguration(exclude = {
//        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
//})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        // https://www.cnblogs.com/zyly/p/12286285.html
        //配置不需要登陆验证
        http.authorizeRequests().anyRequest().permitAll().and().logout().permitAll();
    }

}
