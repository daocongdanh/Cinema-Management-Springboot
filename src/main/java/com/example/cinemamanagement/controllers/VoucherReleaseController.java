package com.example.cinemamanagement.controllers;

import com.example.cinemamanagement.dtos.VoucherReleaseDTO;
import com.example.cinemamanagement.responses.ResponseSuccess;
import com.example.cinemamanagement.services.VoucherReleaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("${api.prefix}/voucherReleases")
@RequiredArgsConstructor
public class VoucherReleaseController {
    private final VoucherReleaseService voucherReleaseService;

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseSuccess> createVoucherRelease(
            @Valid @RequestBody VoucherReleaseDTO voucherReleaseDTO){
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Create voucherRelease successfully")
                .status(HttpStatus.CREATED.value())
                .data(voucherReleaseService.createVoucherRelease(voucherReleaseDTO))
                .build());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseSuccess> getVoucherReleaseById(@PathVariable("id") long id){
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Get voucherRelease information successfully")
                .status(HttpStatus.OK.value())
                .data(voucherReleaseService.getVoucherReleaseById(id))
                .build());
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseSuccess> getAllVoucherReleases(){
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Get all voucherRelease information successfully")
                .status(HttpStatus.OK.value())
                .data(voucherReleaseService.getAllVoucherReleases())
                .build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseSuccess> updateVoucherRelease(
            @PathVariable long id, @Valid @RequestBody VoucherReleaseDTO voucherReleaseDTO){
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Update voucherRelease successfully")
                .status(HttpStatus.ACCEPTED.value())
                .data(voucherReleaseService.updateVoucherRelease(id, voucherReleaseDTO))
                .build());
    }
}
