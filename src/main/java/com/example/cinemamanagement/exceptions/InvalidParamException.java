package com.example.cinemamanagement.exceptions;

public class InvalidParamException extends RuntimeException{
    public InvalidParamException(String message){
        super(message);
    }
}
