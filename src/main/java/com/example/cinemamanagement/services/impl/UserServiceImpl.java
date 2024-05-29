package com.example.cinemamanagement.services.impl;

import com.example.cinemamanagement.dtos.UserDTO;
import com.example.cinemamanagement.exceptions.DuplicateValueException;
import com.example.cinemamanagement.exceptions.ResourceNotFoundException;
import com.example.cinemamanagement.models.User;
import com.example.cinemamanagement.repositories.UserRepository;
import com.example.cinemamanagement.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    @Transactional
    public User createUser(UserDTO userDTO) {
        if(userRepository.existsByUsername(userDTO.getUsername()))
            throw new DuplicateValueException("Username already exists");
        User user = User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .phone(userDTO.getPhone())
                .email(userDTO.getEmail())
                .gender(userDTO.isGender())
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .active(userDTO.isActive())
                .build();
        return userRepository.save(user);
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User updateUser(long id, UserDTO userDTO) {
        User user = getUserById(id);
        if(!user.getUsername().equals(userDTO.getUsername())
                && userRepository.existsByUsername(userDTO.getUsername())){
            throw new DuplicateValueException("Username already exists");
        }
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhone(userDTO.getPhone());
        user.setEmail(userDTO.getEmail());
        user.setGender(userDTO.isGender());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setActive(userDTO.isActive());
        return userRepository.save(user);
    }
}
