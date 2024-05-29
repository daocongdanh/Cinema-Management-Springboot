package com.example.cinemamanagement.services.impl;

import com.example.cinemamanagement.dtos.SeatDTO;
import com.example.cinemamanagement.exceptions.ResourceNotFoundException;
import com.example.cinemamanagement.models.Room;
import com.example.cinemamanagement.models.Seat;
import com.example.cinemamanagement.repositories.SeatRepository;
import com.example.cinemamanagement.responses.SeatResponse;
import com.example.cinemamanagement.services.RoomService;
import com.example.cinemamanagement.services.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {
    private final SeatRepository seatRepository;
    private final RoomService roomService;
    @Override
    @Transactional
    public SeatResponse createSeat(SeatDTO seatDTO) {
        Room room = roomService.getRoomById(seatDTO.getRoomId());
        Seat seat = seatRepository.save(Seat.builder()
                .seatType(seatDTO.getSeatType())
                .room(room)
                .build());
        return SeatResponse.fromSeat(seat);
    }

    @Override
    public List<SeatResponse> getSeatsByRoom(long roomId) {
        Room room = roomService.getRoomById(roomId);
        return seatRepository.findAllByRoom(room)
                .stream()
                .map(SeatResponse::fromSeat)
                .toList();
    }

    @Override
    @Transactional
    public SeatResponse updateSeat(long id, SeatDTO seatDTO) {
        Room room = roomService.getRoomById(seatDTO.getRoomId());
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seat not found"));
        seat.setSeatType(seatDTO.getSeatType());
        seat.setRoom(room);
        return SeatResponse.fromSeat(seatRepository.save(seat));
    }

    @Override
    @Transactional
    public void deleteSeat(long id) {
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seat not found"));
        seatRepository.delete(seat);
    }
}
