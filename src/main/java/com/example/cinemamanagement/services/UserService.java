package com.example.cinemamanagement.services;

import com.example.cinemamanagement.dtos.UserDTO;
import com.example.cinemamanagement.models.User;

import java.util.List;

public interface UserService {

    User createUser(UserDTO userDTO);
    User getUserById(long id);
    List<User> getAllUsers();
    User updateUser(long id, UserDTO userDTO);
}
