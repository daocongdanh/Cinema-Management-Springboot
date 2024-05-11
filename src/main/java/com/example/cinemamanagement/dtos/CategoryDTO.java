package com.example.cinemamanagement.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CategoryDTO {
    @NotBlank(message = "categoryName must be not blank")
    private String categoryName;
}
