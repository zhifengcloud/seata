package com.yzf.sentnel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
@RestController
public class DashboardController {

    @GetMapping("/dash")
    public String dash(){
        return "Hello sentinel-Dash";
    }
}
