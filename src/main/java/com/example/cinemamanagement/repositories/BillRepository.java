package com.example.cinemamanagement.repositories;

import com.example.cinemamanagement.models.Bill;
import com.example.cinemamanagement.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findAllByUser(User user);
}
