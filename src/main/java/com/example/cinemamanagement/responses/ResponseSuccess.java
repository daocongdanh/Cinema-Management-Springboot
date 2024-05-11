package com.example.cinemamanagement.responses;

import lombok.*;

@Getter
@Builder
public class ResponseSuccess {
    private int status;
    private String message;
    private Object data;
}
