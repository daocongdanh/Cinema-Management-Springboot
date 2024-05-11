package com.example.cinemamanagement.repositories;

import com.example.cinemamanagement.models.MovieType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieTypeRepository extends JpaRepository<MovieType, Long> {
}
