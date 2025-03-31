package com.example.pironeer.repository;

import com.example.pironeer.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Spring Data JPA will automatically implement basic CRUD methods
}