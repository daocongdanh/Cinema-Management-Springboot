package com.example.cinemamanagement.repositories;

import com.example.cinemamanagement.models.Room;
import com.example.cinemamanagement.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findAllByRoom(Room room);
}
