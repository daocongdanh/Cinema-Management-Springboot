package com.example.cinemamanagement.controllers;

import com.example.cinemamanagement.dtos.MovieDTO;
import com.example.cinemamanagement.responses.ResponseSuccess;
import com.example.cinemamanagement.services.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/movies")
public class MovieController {
    private final MovieService movieService;

    @PostMapping(value = "")
    public ResponseEntity<ResponseSuccess> createMovie(@ModelAttribute @Valid MovieDTO movieDTO){
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Create movie successfully")
                .status(HttpStatus.CREATED.value())
                .data(movieService.createMovie(movieDTO))
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseSuccess> getMovieById(@PathVariable("id") long id){
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Get movie information successfully")
                .status(HttpStatus.OK.value())
                .data(movieService.getMovieById(id))
                .build());
    }

    @GetMapping("")
    public ResponseEntity<ResponseSuccess> getAllMovies(){
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Get all movie information successfully")
                .status(HttpStatus.OK.value())
                .data(movieService.getAllMovies())
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseSuccess> updateMovie(@PathVariable("id") long id,
                                                         @ModelAttribute @Valid MovieDTO movieDTO){
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Update movie successfully")
                .status(HttpStatus.ACCEPTED.value())
                .data(movieService.updateMovie(id, movieDTO))
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseSuccess> deleteProduct(@PathVariable("id") long id){
        movieService.deleteMovie(id);
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Delete movie successfully")
                .status(HttpStatus.NO_CONTENT.value())
                .build());
    }
}
