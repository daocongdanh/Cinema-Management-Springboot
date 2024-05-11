package com.example.cinemamanagement.responses;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseError{
    private int status;
    private String message;
}
