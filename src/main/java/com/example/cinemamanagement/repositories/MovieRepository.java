package com.example.cinemamanagement.repositories;

import com.example.cinemamanagement.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    boolean existsByMovieName(String movieName);
}
