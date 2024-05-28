package com.example.cinemamanagement.controllers;

import com.example.cinemamanagement.dtos.VoucherDTO;
import com.example.cinemamanagement.responses.ResponseSuccess;
import com.example.cinemamanagement.services.VoucherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/vouchers")
@RequiredArgsConstructor
public class VoucherController {
    private final VoucherService voucherService;

    @PostMapping("")
    public ResponseEntity<ResponseSuccess> createVoucher(@Valid @RequestBody VoucherDTO voucherDTO){
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Create voucher successfully")
                .status(HttpStatus.CREATED.value())
                .data(voucherService.createVoucher(voucherDTO))
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseSuccess> getVoucherById(@PathVariable("id") long id){
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Get voucher information successfully")
                .status(HttpStatus.OK.value())
                .data(voucherService.getVoucherById(id))
                .build());
    }

    @GetMapping("/voucherRelease/{vrId}")
    public ResponseEntity<ResponseSuccess> getVouchersByVoucherRelease(@PathVariable("vrId") long vrId){
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Get all voucher by voucherRelease information successfully")
                .status(HttpStatus.OK.value())
                .data(voucherService.getVouchersByVoucherRelease(vrId))
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseSuccess> updateVoucher(@PathVariable("id") long id,
                                                         @RequestBody @Valid VoucherDTO voucherDTO){
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Update voucher successfully")
                .status(HttpStatus.ACCEPTED.value())
                .data(voucherService.updateVoucher(id, voucherDTO))
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseSuccess> deleteVoucher(@PathVariable("id") long id){
        voucherService.deleteVoucher(id);
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Delete voucher successfully")
                .status(HttpStatus.NO_CONTENT.value())
                .build());
    }
}
