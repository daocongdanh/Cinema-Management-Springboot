package com.example.cinemamanagement.services.impl;

import com.example.cinemamanagement.dtos.MovieTypeDTO;
import com.example.cinemamanagement.exceptions.DuplicateValueException;
import com.example.cinemamanagement.exceptions.ResourceNotFoundException;
import com.example.cinemamanagement.models.Category;
import com.example.cinemamanagement.models.MovieType;
import com.example.cinemamanagement.repositories.MovieTypeRepository;
import com.example.cinemamanagement.services.MovieTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieTypeServiceImpl implements MovieTypeService {
    private final MovieTypeRepository movieTypeRepository;
    @Override
    @Transactional
    public MovieType createMovieType(MovieTypeDTO movieTypeDTO) {
        if(movieTypeRepository.existsByName(movieTypeDTO.getName())){
            throw new DuplicateValueException("MovieType name already exists");
        }
        else{
            MovieType movieType = MovieType.builder()
                    .name(movieTypeDTO.getName())
                    .build();
            return movieTypeRepository.save(movieType);
        }
    }

    @Override
    public MovieType getMovieTypeById(long id) {
        return movieTypeRepository.findById(id).orElseThrow(()
            -> new ResourceNotFoundException("MovieType not found"));
    }

    @Override
    public List<MovieType> getAllMovieTypes() {
        return movieTypeRepository.findAll();
    }

    @Override
    @Transactional
    public MovieType updateMovieType(long id, MovieTypeDTO movieTypeDTO) {
        MovieType movieType = getMovieTypeById(id);
        if(!movieType.getName().equals(movieTypeDTO.getName())
                && movieTypeRepository.existsByName(movieTypeDTO.getName())){
            throw new DuplicateValueException("MovieType name already exists");
        }
        movieType.setName(movieTypeDTO.getName());
        return movieTypeRepository.save(movieType);
    }
}
