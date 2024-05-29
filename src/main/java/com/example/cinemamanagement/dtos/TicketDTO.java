package com.example.cinemamanagement.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO {

    @JsonProperty("show_time_id")
    private Long showTimeId;

    @JsonProperty("seat_id")
    private Long seatId;

}
