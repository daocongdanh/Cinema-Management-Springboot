package com.example.cinemamanagement.controllers;

import com.example.cinemamanagement.dtos.BillDTO;
import com.example.cinemamanagement.responses.ResponseSuccess;
import com.example.cinemamanagement.services.BillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/bills")
@RequiredArgsConstructor
public class BillController {
    private final BillService billService;

    @PostMapping("")
    public ResponseEntity<ResponseSuccess> createBill(@Valid @RequestBody BillDTO billDTO){
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Create bill successfully")
                .status(HttpStatus.CREATED.value())
                .data(billService.createBill(billDTO))
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseSuccess> getBillById(@PathVariable("id") long id){
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Get bill information successfully")
                .status(HttpStatus.OK.value())
                .data(billService.getBillById(id))
                .build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ResponseSuccess> getBillsByUser(@PathVariable("userId") long userId){
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Get all bill by user information successfully")
                .status(HttpStatus.OK.value())
                .data(billService.getBillsByUser(userId))
                .build());
    }

    @GetMapping("")
    public ResponseEntity<ResponseSuccess> getAllBills(){
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Get all bill information successfully")
                .status(HttpStatus.OK.value())
                .data(billService.getAllBills())
                .build());
    }

}
