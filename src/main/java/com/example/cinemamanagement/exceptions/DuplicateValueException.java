package com.example.cinemamanagement.exceptions;

public class DuplicateValueException extends RuntimeException{
    public DuplicateValueException(String message){
        super(message);
    }
}
