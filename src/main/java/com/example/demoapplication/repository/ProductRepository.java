package com.example.demoapplication.repository;

import com.example.demoapplication.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.orders WHERE LOWER(p.name) = LOWER(:name)")
    Optional<Product> findByNameIgnoreCase(String name);

    @Query("SELECT p FROM Product p WHERE LOWER(p.city) = LOWER(:city)")
    List<Product> findByCityIgnoreCase(String city);

    @Query("SELECT p FROM Product p WHERE p.age < :age ORDER BY p.age ASC")
    List<Product> findByAgeLessThanSorted(int age);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) = LOWER(:name) AND LOWER(p.surname) = LOWER(:surname)")
    Optional<Product> findByNameAndSurnameIgnoreCase(String name, String surname);

    @Query("SELECT DISTINCT p FROM Product p JOIN p.orders o WHERE LOWER(o.productName) = LOWER(:productName)")
    List<Product> findByOrderProductNameIgnoreCase(String productName);
}
