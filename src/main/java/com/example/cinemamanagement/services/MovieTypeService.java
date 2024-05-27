package com.example.cinemamanagement.services;


import com.example.cinemamanagement.dtos.MovieTypeDTO;
import com.example.cinemamanagement.models.MovieType;

import java.util.List;

public interface MovieTypeService {

    MovieType createMovieType(MovieTypeDTO movieTypeDTO);
    MovieType getMovieTypeById(long id);
    List<MovieType> getAllMovieTypes();
    MovieType updateMovieType(long id, MovieTypeDTO movieTypeDTO);
}
