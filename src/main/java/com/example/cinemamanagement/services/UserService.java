package com.example.cinemamanagement.services;

import com.example.cinemamanagement.dtos.LoginDTO;
import com.example.cinemamanagement.dtos.RegisterDTO;
import com.example.cinemamanagement.responses.LoginResponse;
import com.example.cinemamanagement.responses.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(RegisterDTO registerDTO);
    UserResponse getUserById(long id);
    List<UserResponse> getAllUsers();
    UserResponse updateUser(long id, RegisterDTO registerDTO);
    LoginResponse login(LoginDTO loginDTO);
}
