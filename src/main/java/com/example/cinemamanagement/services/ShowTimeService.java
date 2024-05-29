package com.example.cinemamanagement.services;

import com.example.cinemamanagement.dtos.ShowTimeDTO;
import com.example.cinemamanagement.responses.ShowTimeResponse;

public interface ShowTimeService {

    ShowTimeResponse createShowTime(ShowTimeDTO showTimeDTO);
    ShowTimeResponse getShowTimeById(long id);

}
