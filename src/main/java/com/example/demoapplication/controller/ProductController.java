package com.example.demoapplication.controller;

import com.example.demoapplication.model.OrderInfo;
import com.example.demoapplication.model.ProductResponse;
import com.example.demoapplication.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/fetch-product")
    public ProductResponse fetchProduct(@RequestParam String name) {
        List<OrderInfo> orders = repository.getOrdersByName(name);
        return new ProductResponse(name, orders);
    }
}