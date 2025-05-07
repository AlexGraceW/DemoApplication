package com.example.demoapplication.repository;

import com.example.demoapplication.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    public List<Product> findByProductName(String productName) {
        return entityManager.createQuery(
                "SELECT DISTINCT p FROM Product p JOIN p.orders o WHERE LOWER(o.productName) = LOWER(:productName)",
                Product.class
        ).setParameter("productName", productName).getResultList();
    }


    public List<Product> findByCity(String city) {
        return entityManager.createQuery(
                "SELECT p FROM Product p WHERE LOWER(p.city) = LOWER(:city)",
                Product.class
        ).setParameter("city", city).getResultList();
    }

    public List<Product> findByAgeLessThanSorted(int age) {
        return entityManager.createQuery(
                "SELECT p FROM Product p WHERE p.age < :age ORDER BY p.age ASC",
                Product.class
        ).setParameter("age", age).getResultList();
    }

    public List<Product> findAll() {
        return entityManager.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }

    public Optional<Product> findByNameAndSurname(String name, String surname) {
        var query = entityManager.createQuery(
                "SELECT p FROM Product p WHERE LOWER(p.name) = LOWER(:name) AND LOWER(p.surname) = LOWER(:surname)",
                Product.class
        );
        query.setParameter("name", name);
        query.setParameter("surname", surname);
        return query.getResultStream().findFirst();
    }

    @Transactional
    public void save(Product product) {
        entityManager.persist(product);
    }
}
