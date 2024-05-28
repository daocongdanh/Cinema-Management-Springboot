package com.example.cinemamanagement.repositories;

import com.example.cinemamanagement.models.Category;
import com.example.cinemamanagement.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByProductName(String productName);
    List<Product> findByCategory(Category category);
}
