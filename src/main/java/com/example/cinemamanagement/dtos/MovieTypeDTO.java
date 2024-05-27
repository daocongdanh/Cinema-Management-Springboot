package com.example.cinemamanagement.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieTypeDTO {

    @NotBlank(message = "movieTypeName must be not blank")
    private String name;
}
