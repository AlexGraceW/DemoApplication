package com.example.demoapplication.repository;

import com.example.demoapplication.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Product> findByName(String name) {
        var query = entityManager.createQuery(
            "SELECT p FROM Product p LEFT JOIN FETCH p.orders WHERE LOWER(p.name) = LOWER(:name)",
            Product.class
        );
        query.setParameter("name", name);
        return query.getResultStream().findFirst();
    }

    @Transactional
    public void save(Product product) {
        entityManager.persist(product);
    }
}