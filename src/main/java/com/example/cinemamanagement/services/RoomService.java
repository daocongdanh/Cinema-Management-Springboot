package com.example.cinemamanagement.services;


import com.example.cinemamanagement.dtos.RoomDTO;
import com.example.cinemamanagement.models.Room;

import java.util.List;

public interface RoomService {
    Room createRoom(RoomDTO roomDTO);
    Room getRoomById(long id);
    List<Room> getAllRooms();
    Room updateRoom(long id, RoomDTO roomDTO);
    void deleteRoom(long id);
}
