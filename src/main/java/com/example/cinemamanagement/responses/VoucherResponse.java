package com.example.cinemamanagement.responses;

import com.example.cinemamanagement.models.Voucher;
import com.example.cinemamanagement.models.VoucherRelease;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "dd-MM-yyy HH:mm:ss")
    private LocalDateTime usedAt;

    @JsonFormat(pattern = "dd-MM-yyy HH:mm:ss")
    private LocalDateTime releasedAt;

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
