package io.seata.order.controller;


import io.seata.order.client.ProductClient;
import io.seata.order.service.OrderService;
import io.seata.order.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductClient productClient;

    @PutMapping("/order/{productId}")
    public Boolean seataDemo(@PathVariable(value = "productId") long productId, int num,
                             @RequestParam(required = false, defaultValue = "false") Boolean hasError) {
        boolean result = orderService.seataDemo(productId, num, hasError);
        return result;
    }

    @GetMapping("/order/{productId}")
    public ProductVo getProduct(@PathVariable(value = "productId") int productId) {
        return productClient.getById(productId);
    }

    @GetMapping("/order/test")
    public String test() {
        return "hello order-sharding-seata";
    }
}
