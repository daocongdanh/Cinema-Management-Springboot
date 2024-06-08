package com.example.cinemamanagement.services;

import com.example.cinemamanagement.models.Token;
import com.example.cinemamanagement.models.User;

public interface TokenService {
    Token addToken(User user, String token, boolean isMobile);
    void deleteToken(String token);
    Token getTokenByRefreshToken(String refreshToken);
    Token updateToken(Token token);
}
