package com.example.cinemamanagement.controllers;

import com.example.cinemamanagement.dtos.MovieTypeDTO;
import com.example.cinemamanagement.models.MovieType;
import com.example.cinemamanagement.responses.ResponseSuccess;
import com.example.cinemamanagement.services.MovieTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/movieTypes")
@RequiredArgsConstructor
public class MovieTypeController {
    private final MovieTypeService movieTypeService;

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseSuccess> createMovieType(
            @Valid @RequestBody MovieTypeDTO movieTypeDTO){
        MovieType movieType = movieTypeService.createMovieType(movieTypeDTO);
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                        .message("Create movieType successfully")
                        .status(HttpStatus.CREATED.value())
                        .data(movieType)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseSuccess> getMovieTypeById(@PathVariable("id") long id){
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Get movieType information successfully")
                .status(HttpStatus.OK.value())
                .data(movieTypeService.getMovieTypeById(id))
                .build());
    }

    @GetMapping("")
    public ResponseEntity<ResponseSuccess> getAllMovieTypes(){
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Get all movieType information successfully")
                .status(HttpStatus.OK.value())
                .data(movieTypeService.getAllMovieTypes())
                .build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseSuccess> updateMovieType(@PathVariable("id") long id,
                                                           @Valid @RequestBody MovieTypeDTO movieTypeDTO){
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Update movieType successfully")
                .status(HttpStatus.ACCEPTED.value())
                .data(movieTypeService.updateMovieType(id, movieTypeDTO))
                .build());
    }
}
