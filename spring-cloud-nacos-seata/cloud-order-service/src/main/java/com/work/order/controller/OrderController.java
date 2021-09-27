package com.work.order.controller;

import com.work.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Program Name: springcloud-nacos-seata
 * <p>
 * Description:
 * <p>
 *
 * @author zhangjianwei
 * @version 1.0
 * @date 2019/8/28 4:05 PM
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/test")
    public String test(){
        return "test:"+orderService;
    }

    /**
     * 购买下单，模拟全局事务提交
     *
     * @return
     */
    @GetMapping("/purchase/commit")
    public Boolean purchaseCommit(HttpServletRequest request) {
        orderService.create("1001", "2001", 1);
        return true;
    }

    /**
     * 购买下单，模拟全局事务回滚
     *
     * @return
     */
    @GetMapping("/purchase/rollback")
    public Boolean purchaseRollback() {
        try {
            orderService.create("1002", "2001", 1);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
