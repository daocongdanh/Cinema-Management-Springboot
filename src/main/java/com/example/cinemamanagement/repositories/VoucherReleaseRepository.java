package com.example.cinemamanagement.repositories;

import com.example.cinemamanagement.models.VoucherRelease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherReleaseRepository extends JpaRepository<VoucherRelease, Long> {
    boolean existsByRealeaseName(String releaseName);
}
