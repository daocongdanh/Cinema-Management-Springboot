package com.example.cinemamanagement.controllers;

import com.example.cinemamanagement.dtos.UserDTO;
import com.example.cinemamanagement.responses.ResponseSuccess;
import com.example.cinemamanagement.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<ResponseSuccess> createUser(@Valid @RequestBody UserDTO userDTO){
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Create user successfully")
                .status(HttpStatus.CREATED.value())
                .data(userService.createUser(userDTO))
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseSuccess> getUserById(@PathVariable("id") long id){
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Get user information successfully")
                .status(HttpStatus.OK.value())
                .data(userService.getUserById(id))
                .build());
    }

    @GetMapping("")
    public ResponseEntity<ResponseSuccess> getAllUsers(){
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Get all user information successfully")
                .status(HttpStatus.OK.value())
                .data(userService.getAllUsers())
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseSuccess> updateUser(@PathVariable("id") long id,
                                                      @Valid @RequestBody UserDTO userDTO){
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Update user successfully")
                .status(HttpStatus.ACCEPTED.value())
                .data(userService.updateUser(id, userDTO))
                .build());
    }
}
