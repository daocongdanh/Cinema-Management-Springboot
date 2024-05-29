package com.example.cinemamanagement.responses;

import com.example.cinemamanagement.models.ShowTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowTimeResponse {

    private Long id;

    @JsonProperty("ticket_price")
    private double ticketPrice;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @JsonProperty("start_time")
    private LocalDateTime startTime;

    @JsonProperty("movie_id")
    private Long movieId;

    @JsonProperty("room_id")
    private Long roomId;

    public static ShowTimeResponse fromShowTime(ShowTime showTime){
        return ShowTimeResponse.builder()
                .id(showTime.getId())
                .ticketPrice(showTime.getTicketPrice())
                .startTime(showTime.getStartTime())
                .movieId(showTime.getMovie().getId())
                .roomId(showTime.getRoom().getId())
                .build();
    }
}
