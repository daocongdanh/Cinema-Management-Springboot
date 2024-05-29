package com.example.cinemamanagement.services.impl;

import com.example.cinemamanagement.dtos.MovieDTO;
import com.example.cinemamanagement.enums.MovieStatus;
import com.example.cinemamanagement.exceptions.DuplicateValueException;
import com.example.cinemamanagement.exceptions.ResourceNotFoundException;
import com.example.cinemamanagement.models.Movie;
import com.example.cinemamanagement.models.MovieType;
import com.example.cinemamanagement.repositories.MovieRepository;
import com.example.cinemamanagement.responses.MovieResponse;
import com.example.cinemamanagement.services.MovieService;
import com.example.cinemamanagement.services.MovieTypeService;
import com.example.cinemamanagement.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final MovieTypeService movieTypeService;
    private final FileUtil fileUtil;
    @Override
    @Transactional
    public MovieResponse createMovie(MovieDTO movieDTO) {
        if(movieDTO.getPoster() == null)
            throw new RuntimeException("poster must be not blank");
        if(movieRepository.existsByMovieName(movieDTO.getMovieName()))
            throw new DuplicateValueException("Movie name already exists");
        MovieType movieType = movieTypeService.getMovieTypeById(movieDTO.getMovieTypeId());
        String poster = fileUtil.createFile(movieDTO.getPoster());
        Movie movie = movieRepository.save(Movie.builder()
                .movieName(movieDTO.getMovieName())
                .runningTime(movieDTO.getRunningTime())
                .country(movieDTO.getCountry())
                .language(movieDTO.getLanguage())
                .description(movieDTO.getDescription())
                .releaseYear(movieDTO.getReleaseYear())
                .author(movieDTO.getAuthor())
                .poster(poster)
                .trailer(movieDTO.getTrailer())
                .movieStatus(movieDTO.getMovieStatus())
                .movieType(movieType)
                .build());
        return MovieResponse.fromMovie(movie);
    }

    @Override
    public MovieResponse getMovieById(long id) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if(movie == null)
            throw new ResourceNotFoundException("Movie not found");
        return MovieResponse.fromMovie(movie);
    }

    @Override
    public List<MovieResponse> getAllMovies() {
        return movieRepository.findAll().stream()
                .map(MovieResponse::fromMovie)
                .toList();
    }

    @Override
    @Transactional
    public MovieResponse updateMovie(long id, MovieDTO movieDTO) {
        Movie movie = movieRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Movie not found")
        );
        MovieType movieType = movieTypeService.getMovieTypeById(movieDTO.getMovieTypeId());
        if(!movieType.getName().equals(movie.getAuthor())
                && movieRepository.existsByMovieName(movieDTO.getMovieName())){
            throw new DuplicateValueException("Movie name already exists");
        }
        movie.setMovieName(movieDTO.getMovieName());
        movie.setRunningTime(movieDTO.getRunningTime());
        movie.setCountry(movieDTO.getCountry());
        movie.setLanguage(movieDTO.getLanguage());
        movie.setDescription(movieDTO.getDescription());
        movie.setReleaseYear(movieDTO.getReleaseYear());
        movie.setAuthor(movieDTO.getAuthor());
        movie.setTrailer(movieDTO.getTrailer());
        movie.setMovieStatus(movieDTO.getMovieStatus());
        movie.setMovieType(movieType);
        if(!movieDTO.getPoster().getOriginalFilename().equals("")){ // Cập nhật lại image
            String poster = fileUtil.updateFile(movieDTO.getPoster(), movie.getPoster());
            movie.setPoster(poster);
        }
        return MovieResponse.fromMovie(movieRepository.save(movie));
    }

    @Override
    @Transactional
    public void deleteMovie(long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
        movie.setMovieStatus(MovieStatus.STOPPED);
        movieRepository.save(movie);
    }
}
