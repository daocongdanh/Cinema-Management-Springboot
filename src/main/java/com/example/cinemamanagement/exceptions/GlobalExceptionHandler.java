package com.example.cinemamanagement.exceptions;

import com.example.cinemamanagement.responses.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseError> handleException(Exception e){
        return ResponseEntity.internalServerError().body(
                ResponseError.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(e.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseError> handleDataNotFoundException(DataNotFoundException e){
        return ResponseEntity.internalServerError().body(
                ResponseError.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message(e.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(DuplicateValueException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseError> handleDuplicateValueException(DuplicateValueException e){
        return ResponseEntity.badRequest().body(
                ResponseError.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message(e.getMessage())
                        .build()
        );
    }
}
