package com.example.cinemamanagement.controllers;

import com.example.cinemamanagement.dtos.SeatDTO;
import com.example.cinemamanagement.responses.ResponseSuccess;
import com.example.cinemamanagement.services.SeatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/seats")
@RequiredArgsConstructor
public class SeatController {
    private final SeatService seatService;

    @PostMapping("")
    public ResponseEntity<ResponseSuccess> createSeat(@Valid @RequestBody SeatDTO seatDTO){
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Create seat successfully")
                .status(HttpStatus.CREATED.value())
                .data(seatService.createSeat(seatDTO))
                .build());
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<ResponseSuccess> getSeatsByRoom(@PathVariable("roomId") long roomId){
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Get all seat by room information successfully")
                .status(HttpStatus.OK.value())
                .data(seatService.getSeatsByRoom(roomId))
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseSuccess> updateSeat(@PathVariable("id") long id,
                                                      @Valid @RequestBody SeatDTO seatDTO){
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Update seat successfully")
                .status(HttpStatus.ACCEPTED.value())
                .data(seatService.updateSeat(id, seatDTO))
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseSuccess> deleteSeat(@PathVariable("id") long id){
        seatService.deleteSeat(id);
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Delete seat successfully")
                .status(HttpStatus.NO_CONTENT.value())
                .build());
    }
}
