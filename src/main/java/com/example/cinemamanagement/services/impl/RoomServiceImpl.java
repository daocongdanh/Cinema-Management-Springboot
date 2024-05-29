package com.example.cinemamanagement.services.impl;

import com.example.cinemamanagement.dtos.RoomDTO;
import com.example.cinemamanagement.enums.RoomStatus;
import com.example.cinemamanagement.exceptions.DuplicateValueException;
import com.example.cinemamanagement.exceptions.ResourceNotFoundException;
import com.example.cinemamanagement.models.Room;
import com.example.cinemamanagement.repositories.RoomRepository;
import com.example.cinemamanagement.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    @Override
    @Transactional
    public Room createRoom(RoomDTO roomDTO) {
        if(roomRepository.existsByRoomName(roomDTO.getRoomName()))
            throw new DuplicateValueException("Room name already exists");
        Room room = Room.builder()
                .roomName(roomDTO.getRoomName())
                .roomStatus(roomDTO.getRoomStatus())
                .build();
        return roomRepository.save(room);
    }

    @Override
    public Room getRoomById(long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    @Transactional
    public Room updateRoom(long id, RoomDTO roomDTO) {
        Room room = getRoomById(id);
        if(!room.getRoomName().equals(roomDTO.getRoomName())
                && roomRepository.existsByRoomName(roomDTO.getRoomName())){
            throw new DuplicateValueException("Room name already exists");
        }
        room.setRoomName(roomDTO.getRoomName());
        room.setRoomStatus(roomDTO.getRoomStatus());
        return roomRepository.save(room);
    }

    @Override
    @Transactional
    public void deleteRoom(long id) {
        Room room = getRoomById(id);
        room.setRoomStatus(RoomStatus.MAINTENANCE);
        roomRepository.save(room);
    }
}
