package io.seata.order.client;

import io.seata.order.vo.ProductVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("product-server")
public interface ProductClient {

    @PutMapping("/product/{productId}")
    Void minusStock(@PathVariable(value = "productId") int productId, @RequestParam("num") int num);

    @GetMapping("/product/{productId}")
    ProductVo getById(@PathVariable(value = "productId") int productId);

}
