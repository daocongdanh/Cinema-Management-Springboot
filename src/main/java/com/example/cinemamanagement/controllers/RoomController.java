package com.example.cinemamanagement.controllers;

import com.example.cinemamanagement.dtos.RoomDTO;
import com.example.cinemamanagement.responses.ResponseSuccess;
import com.example.cinemamanagement.services.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseSuccess> createRoom(@Valid @RequestBody RoomDTO roomDTO){
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Create room successfully")
                .status(HttpStatus.CREATED.value())
                .data(roomService.createRoom(roomDTO))
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseSuccess> getRoomById(@PathVariable("id") long id){
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Get room information successfully")
                .status(HttpStatus.OK.value())
                .data(roomService.getRoomById(id))
                .build());
    }

    @GetMapping("")
    public ResponseEntity<ResponseSuccess> getAllRooms(){
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Get all room information successfully")
                .status(HttpStatus.OK.value())
                .data(roomService.getAllRooms())
                .build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseSuccess> updateRoom(@PathVariable("id") long id,
                                                      @Valid @RequestBody RoomDTO roomDTO){
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Update room successfully")
                .status(HttpStatus.ACCEPTED.value())
                .data(roomService.updateRoom(id, roomDTO))
                .build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseSuccess> deleteRoom(@PathVariable("id") long id){
        roomService.deleteRoom(id);
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Delete room successfully")
                .status(HttpStatus.NO_CONTENT.value())
                .build());
    }
}
