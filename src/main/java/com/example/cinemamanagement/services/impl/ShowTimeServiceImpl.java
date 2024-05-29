package com.example.cinemamanagement.services.impl;

import com.example.cinemamanagement.dtos.ShowTimeDTO;
import com.example.cinemamanagement.exceptions.InvalidParamException;
import com.example.cinemamanagement.exceptions.ResourceNotFoundException;
import com.example.cinemamanagement.models.Movie;
import com.example.cinemamanagement.models.Room;
import com.example.cinemamanagement.models.ShowTime;
import com.example.cinemamanagement.repositories.MovieRepository;
import com.example.cinemamanagement.repositories.RoomRepository;
import com.example.cinemamanagement.repositories.ShowTimeRepository;
import com.example.cinemamanagement.responses.ShowTimeResponse;
import com.example.cinemamanagement.services.ShowTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowTimeServiceImpl implements ShowTimeService {
    private final ShowTimeRepository showTimeRepository;
    private final RoomRepository roomRepository;
    private final MovieRepository movieRepository;

    @Override
    @Transactional
    public ShowTimeResponse createShowTime(ShowTimeDTO showTimeDTO) {
        Room room = roomRepository.findById(showTimeDTO.getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));
        Movie movie = movieRepository.findById(showTimeDTO.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
        LocalDateTime startTime = showTimeDTO.getStartTime();
        LocalDateTime endTime = startTime.plusMinutes(movie.getRunningTime());
        if (startTime.isBefore(LocalDateTime.now()))
            throw new InvalidParamException("Showtime must be before the current date");
        if (endTime.isAfter(LocalDateTime.of(startTime.toLocalDate(), LocalTime.of(23, 59))))
            throw new InvalidParamException("Showtime can only be created during the day");
        if(!checkInsertShowTime(startTime, endTime, room))
            throw new InvalidParamException("Showtime conflicts");
        ShowTime showTime = showTimeRepository.save(ShowTime.builder()
                .ticketPrice(showTimeDTO.getTicketPrice())
                .startTime(showTimeDTO.getStartTime())
                .movie(movie)
                .room(room)
                .build());
        return ShowTimeResponse.fromShowTime(showTime);
    }
    private boolean checkInsertShowTime(LocalDateTime startTime, LocalDateTime endTime,Room room){
        List<ShowTime> showTimes = showTimeRepository.findAllByRoomAndTime(room, startTime.toLocalDate());
        showTimes.sort((o1, o2) -> {
            if (o1.getStartTime().isBefore(o2.getStartTime())) {
                return -1;
            }
            return 1;
        });
        if (showTimes.isEmpty()) { // TH1: Chưa có suất chiếu nào trong ngày
            return true;
        }
        if (endTime.isBefore(showTimes.get(0).getStartTime())) {// TH2: Thêm ở đầu
            return true;
        }
        ShowTime showtimeEnd = showTimes.get(showTimes.size() - 1);

        if (showtimeEnd.getStartTime().plusMinutes(showtimeEnd.getMovie().getRunningTime())
                .isBefore(startTime)) { // TH3: Thêm ở cuối
            return true;
        }
        for (int i = 0; i < showTimes.size() - 1; i++) { // TH4: Thêm ở giữa
            ShowTime showtime1 = showTimes.get(i);
            ShowTime showtime2 = showTimes.get(i + 1);
            LocalDateTime start = showtime1.getStartTime().plusMinutes(showtime1.getMovie().getRunningTime());
            if (start.isBefore(startTime) && endTime.isBefore(showtime2.getStartTime())) {
                return true;
            }
        }
        return false;
    }
    @Override
    public ShowTimeResponse getShowTimeById(long id) {
        ShowTime showTime = showTimeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ShowTime not found"));
        return ShowTimeResponse.fromShowTime(showTime);
    }
}
