package com.example.cinemamanagement.dtos;

import com.example.cinemamanagement.models.VoucherRelease;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoucherDTO {

    @NotBlank(message = "code must be not blank")
    private String code;

    private boolean status;

    @JsonFormat(pattern = "dd-MM-yyy HH:mm:ss")
    @JsonProperty("used_at")
    private LocalDateTime usedAt;

    @JsonFormat(pattern = "dd-MM-yyy HH:mm:ss")
    @JsonProperty("released_at")
    private LocalDateTime releasedAt;

    @JsonProperty("voucher_release_id")
    private Long voucherReleaseId;
}
