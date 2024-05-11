package com.example.cinemamanagement.repositories;

import com.example.cinemamanagement.models.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long> {
}
