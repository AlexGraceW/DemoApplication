package com.example.demoapplication.controller;

import com.example.demoapplication.entity.Product;
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
    public Product fetchProduct(@RequestParam String name) {
        return repository.findByNameIgnoreCase(name).orElse(null);
    }

    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product) {
        if (product.getOrders() != null) {
            product.getOrders().forEach(order -> order.setProduct(product));
        }
        return repository.save(product);
    }

    @GetMapping("/by-city")
    public List<Product> getByCity(@RequestParam String city) {
        return repository.findByCityIgnoreCase(city);
    }

    @GetMapping("/by-age-less-than")
    public List<Product> getByAgeLessThan(@RequestParam int age) {
        return repository.findByAgeLessThanSorted(age);
    }

    @GetMapping("/by-name-surname")
    public Product getByNameAndSurname(@RequestParam String name, @RequestParam String surname) {
        return repository.findByNameAndSurnameIgnoreCase(name, surname).orElse(null);
    }

    @GetMapping("/by-product-name")
    public List<Product> getByProductName(@RequestParam String productName) {
        return repository.findByOrderProductNameIgnoreCase(productName);
    }

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return repository.findAll();
    }
}
