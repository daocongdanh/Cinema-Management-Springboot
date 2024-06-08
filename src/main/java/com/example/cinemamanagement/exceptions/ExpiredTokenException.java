package com.example.cinemamanagement.exceptions;

public class ExpiredTokenException extends RuntimeException{
    public ExpiredTokenException(String message){
        super(message);
    }
}
