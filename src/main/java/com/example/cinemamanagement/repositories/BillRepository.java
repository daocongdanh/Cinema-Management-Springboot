package com.example.cinemamanagement.repositories;

import com.example.cinemamanagement.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
}
