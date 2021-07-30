/*
 * author: wangjian (jonath@163.com)
 * Copyright 2019
 */
package com.work.storage.controller;

import com.work.storage.service.StorageService;
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
    private StorageService storageService;

    @GetMapping("test")
    public String get(){
        return "test"+storageService;
    }
}
