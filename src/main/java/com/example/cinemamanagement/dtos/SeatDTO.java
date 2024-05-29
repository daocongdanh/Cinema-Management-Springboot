package com.example.cinemamanagement.dtos;

import com.example.cinemamanagement.enums.SeatType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeatDTO {

    @JsonProperty("seat_type")
    private SeatType seatType;

    @JsonProperty("room_id")
    private long roomId;
}
