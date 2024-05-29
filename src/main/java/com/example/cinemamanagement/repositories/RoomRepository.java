package com.example.cinemamanagement.repositories;

import com.example.cinemamanagement.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    boolean existsByRoomName(String roomName);
}
