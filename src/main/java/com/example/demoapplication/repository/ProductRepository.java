// ProductRepository.java

package com.example.demoapplication.repository;

import com.example.demoapplication.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Найти продукты по городу
    List<Product> findByCity(String city);

    // Найти продукты с возрастом меньше указанного, отсортированные по возрастанию
    List<Product> findByAgeLessThanOrderByAgeAsc(int age);

    // Найти продукт по имени и фамилии (опционально)
    Optional<Product> findByNameAndSurname(String name, String surname);
}
