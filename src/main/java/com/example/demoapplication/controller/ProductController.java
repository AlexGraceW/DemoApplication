package com.example.demoapplication.controller;

import com.example.demoapplication.entity.Product;
import com.example.demoapplication.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product) {
        return repository.save(product);
    }

    @GetMapping("/city/{city}")
    public List<Product> getProductsByCity(@PathVariable String city) {
        return repository.findByCity(city);
    }

    @GetMapping("/age/{age}")
    public List<Product> getProductsByAge(@PathVariable int age) {
        return repository.findByAgeLessThanOrderByAgeAsc(age);
    }

    @GetMapping("/person")
    public Optional<Product> getProductByNameAndSurname(@RequestParam String name, @RequestParam String surname) {
        return repository.findByNameAndSurname(name, surname);
    }
}

