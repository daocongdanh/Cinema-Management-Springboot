package com.example.cinemamanagement.repositories;

import com.example.cinemamanagement.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
}
