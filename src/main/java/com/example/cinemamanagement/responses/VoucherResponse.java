package com.example.cinemamanagement.responses;

import com.example.cinemamanagement.models.Voucher;
import com.example.cinemamanagement.models.VoucherRelease;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoucherResponse {
    private Long id;

    private String code;

    private boolean status;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @JsonProperty("used_at")
    private LocalDateTime usedAt;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @JsonProperty("released_at")
    private LocalDateTime releasedAt;

    @JsonProperty("voucher_release_id")
    private Long voucherReleaseId;

    public static VoucherResponse fromVoucher(Voucher voucher){
        return VoucherResponse.builder()
                .id(voucher.getId())
                .code(voucher.getCode())
                .status(voucher.isStatus())
                .usedAt(voucher.getUsedAt())
                .releasedAt(voucher.getReleasedAt())
                .voucherReleaseId(voucher.getVoucherRelease().getId())
                .build();
    }
}
