package com.example.cinemamanagement.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowTimeDTO {

    @JsonProperty("ticket_price")
    private double ticketPrice;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @JsonProperty("start_time")
    private LocalDateTime startTime;

    @JsonProperty("movie_id")
    private Long movieId;

    @JsonProperty("room_id")
    private Long roomId;
}
