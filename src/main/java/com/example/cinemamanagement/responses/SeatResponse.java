package com.example.cinemamanagement.responses;

import com.example.cinemamanagement.enums.SeatType;
import com.example.cinemamanagement.models.Seat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeatResponse {

    private Long id;

    @JsonProperty("seat_type")
    private SeatType seatType;

    @JsonProperty("room_id")
    private Long roomId;

    public static SeatResponse fromSeat(Seat seat){
        return SeatResponse.builder()
                .id(seat.getId())
                .seatType(seat.getSeatType())
                .roomId(seat.getRoom().getId())
                .build();
    }
}
