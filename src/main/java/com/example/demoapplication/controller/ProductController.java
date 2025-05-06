package com.example.demoapplication.controller;

import com.example.demoapplication.entity.Order;
import com.example.demoapplication.entity.Product;
import com.example.demoapplication.repository.ProductRepository;
import jakarta.transaction.Transactional;
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
    public Product fetchProduct(@RequestParam String name) {
        return repository.findByName(name).orElse(null);
    }

    @PostMapping("/create")
    @Transactional
    public void createProduct(@RequestBody Product product) {
        List<Order> orders = product.getOrders();
        if (orders != null) {
            for (Order order : orders) {
                order.setProduct(product);
            }
        }
        repository.save(product);
    }
}