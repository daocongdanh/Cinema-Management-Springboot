package com.example.cinemamanagement.responses;

import com.example.cinemamanagement.enums.MovieStatus;
import com.example.cinemamanagement.models.Movie;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieResponse {
    private Long id;

    private String movieName;

    private int runningTime;

    private String country;

    private String language;

    private String description;

    private int releaseYear;

    private String author;

    private String poster;

    private String trailer;

    private MovieStatus movieStatus;

    private String movieTypeName;

    public static MovieResponse fromMovie(Movie movie){
        return MovieResponse.builder()
                .id(movie.getId())
                .movieName(movie.getMovieName())
                .runningTime(movie.getRunningTime())
                .country(movie.getCountry())
                .language(movie.getLanguage())
                .description(movie.getDescription())
                .releaseYear(movie.getReleaseYear())
                .author(movie.getAuthor())
                .poster(movie.getPoster())
                .trailer(movie.getTrailer())
                .movieStatus(movie.getMovieStatus())
                .movieTypeName(movie.getMovieType().getName())
                .build();
    }
}
