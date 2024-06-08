package com.example.cinemamanagement.services;

import com.example.cinemamanagement.dtos.LoginDTO;
import com.example.cinemamanagement.dtos.LogoutDTO;
import com.example.cinemamanagement.dtos.RefreshTokenDTO;
import com.example.cinemamanagement.dtos.RegisterDTO;
import com.example.cinemamanagement.responses.LoginResponse;
import com.example.cinemamanagement.responses.UserResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface UserService {

    UserResponse createUser(RegisterDTO registerDTO);
    UserResponse getUserById(long id);
    List<UserResponse> getAllUsers();
    UserResponse updateUser(long id, RegisterDTO registerDTO);
    LoginResponse login(LoginDTO loginDTO, HttpServletRequest request);
    void logout(LogoutDTO logoutDTO);
    LoginResponse refreshToken(RefreshTokenDTO refreshTokenDTO);
}
