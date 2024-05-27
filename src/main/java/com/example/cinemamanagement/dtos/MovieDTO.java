package com.example.cinemamanagement.dtos;

import com.example.cinemamanagement.enums.MovieStatus;
import com.example.cinemamanagement.models.MovieType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {

    @NotBlank(message = "movieName must be not blank")
    private String movieName;

    @NotNull(message = "runningTime must be not null")
    @Min(value = 1, message="runningTime must be greater than or equal to 1")
    private int runningTime;

    @NotBlank(message = "country must be not blank")
    private String country;

    @NotBlank(message = "language must be not blank")
    private String language;

    @NotBlank(message = "description must be not blank")
    private String description;

    @NotNull(message = "releaseYear must be not null")
    @Min(value = 1990, message="releaseYear must be greater than or equal to 1990")
    private int releaseYear;

    @NotBlank(message = "author must be not blank")
    private String author;

    private MultipartFile poster;

    @NotBlank(message = "trailer must be not blank")
    private String trailer;

    private MovieStatus movieStatus;

    @NotNull(message = "movieTypeId must be not null")
    private Long movieTypeId;

}
