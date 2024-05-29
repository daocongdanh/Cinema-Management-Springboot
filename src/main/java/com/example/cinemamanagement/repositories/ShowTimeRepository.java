package com.example.cinemamanagement.repositories;

import com.example.cinemamanagement.models.Movie;
import com.example.cinemamanagement.models.Room;
import com.example.cinemamanagement.models.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShowTimeRepository extends JpaRepository<ShowTime, Long> {
    List<ShowTime> findAllByMovieAndRoom(Movie movie, Room room);

    @Query(value = "select st from ShowTime st where st.room = ?1 " +
                   "and DATE(st.startTime) = ?2")
    List<ShowTime> findAllByRoomAndTime(Room room, LocalDate startTime);
}
