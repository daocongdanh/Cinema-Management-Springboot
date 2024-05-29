package com.example.cinemamanagement.dtos;

import com.example.cinemamanagement.enums.ObjectType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoucherReleaseDTO {

    @NotBlank(message = "movieName must be not blank")
    @JsonProperty("realease_name")
    private String realeaseName;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @JsonProperty("start_date")
    private LocalDate startDate;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @JsonProperty("finish_date")
    private LocalDate finishDate;

    private double price;

    @JsonProperty("min_price")
    private double minPrice;

    @JsonProperty("object_type")
    private ObjectType objectType;
}
