package com.example.cinemamanagement.repositories;

import com.example.cinemamanagement.models.ProductBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductBillRepository extends JpaRepository<ProductBill, Long> {
}
