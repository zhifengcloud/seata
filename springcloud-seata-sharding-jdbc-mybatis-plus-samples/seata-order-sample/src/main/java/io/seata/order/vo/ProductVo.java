/*
 * author: wangjian (jonath@163.com)
 * Copyright 2019
 */
package io.seata.order.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * TODO
 * author: wangjian (jonath@163.com)
 * date: 2021/7/30 17:24
 */
@Data
public class ProductVo {
    private Long id;

    private String productName;

    private Integer stock;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
