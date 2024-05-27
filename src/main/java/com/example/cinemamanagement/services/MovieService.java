package com.example.cinemamanagement.services;


import com.example.cinemamanagement.dtos.MovieDTO;
import com.example.cinemamanagement.responses.MovieResponse;

import java.util.List;

public interface MovieService {

    MovieResponse createMovie(MovieDTO movieDTO);
    MovieResponse getMovieById(long id);
    List<MovieResponse> getAllMovies();
    MovieResponse updateMovie(long id, MovieDTO movieDTO);
    void deleteMovie(long id);
}
