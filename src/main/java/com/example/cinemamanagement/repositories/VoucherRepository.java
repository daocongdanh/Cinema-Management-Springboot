package com.example.cinemamanagement.repositories;

import com.example.cinemamanagement.models.Voucher;
import com.example.cinemamanagement.models.VoucherRelease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long> {
    boolean existsByCode(String code);
    List<Voucher> findByVoucherRelease(VoucherRelease voucherRelease);
}
