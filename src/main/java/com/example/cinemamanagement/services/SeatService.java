package com.example.cinemamanagement.services;

import com.example.cinemamanagement.dtos.SeatDTO;
import com.example.cinemamanagement.responses.SeatResponse;

import java.util.List;

public interface SeatService {
    SeatResponse createSeat(SeatDTO seatDTO);
    List<SeatResponse> getSeatsByRoom(long roomId);
    SeatResponse updateSeat(long id, SeatDTO seatDTO);
    void deleteSeat(long id);

}
