package com.example.cinemamanagement.repositories;

import com.example.cinemamanagement.models.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowTimeRepository extends JpaRepository<ShowTime, Long> {
}
