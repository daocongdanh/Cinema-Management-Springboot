package com.example.cinemamanagement.responses;

import com.example.cinemamanagement.enums.MovieStatus;
import com.example.cinemamanagement.models.Movie;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("movie_name")
    private String movieName;

    @JsonProperty("running_time")
    private int runningTime;

    private String country;

    private String language;

    private String description;

    @JsonProperty("release_year")
    private int releaseYear;

    private String author;

    private String poster;

    private String trailer;

    @JsonProperty("movie_status")
    private MovieStatus movieStatus;

    @JsonProperty("movie_type_name")
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
