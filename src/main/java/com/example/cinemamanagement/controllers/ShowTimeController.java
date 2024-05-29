package com.example.cinemamanagement.controllers;

import com.example.cinemamanagement.dtos.ShowTimeDTO;
import com.example.cinemamanagement.responses.ResponseSuccess;
import com.example.cinemamanagement.services.ShowTimeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/showTimes")
@RequiredArgsConstructor
public class ShowTimeController {
    private final ShowTimeService showTimeService;

    @PostMapping("")
    public ResponseEntity<ResponseSuccess> createShowTime(@Valid @RequestBody ShowTimeDTO showTimeDTO){
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Create ShowTime successfully")
                .status(HttpStatus.CREATED.value())
                .data(showTimeService.createShowTime(showTimeDTO))
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseSuccess> getShowTimeById(@PathVariable("id") long id){
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Get ShowTime information successfully")
                .status(HttpStatus.OK.value())
                .data(showTimeService.getShowTimeById(id))
                .build());
    }
}
