package io.seata.product.controller;


import io.seata.product.entity.Product;
import io.seata.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PutMapping("/product/{productId}")
    public ResponseEntity<Void> minusStock(@PathVariable(value = "productId") int productId, int num) {
        productService.minusStock(productId, num);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/product/{productId}")
    public Product getProduct(@PathVariable(value = "productId") int productId) {
        return productService.getByProductId(productId);
    }

    @GetMapping("/product/test")
    public String test(){
        return "hello-product";
    }
}
