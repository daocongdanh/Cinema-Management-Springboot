package com.example.cinemamanagement.repositories;

import com.example.cinemamanagement.models.Token;
import com.example.cinemamanagement.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    List<Token> findAllByUser(User user);
    Optional<Token> findByToken(String token);
    boolean existsByToken(String token);
    Optional<Token> findByRefreshToken(String refreshToken);
}
