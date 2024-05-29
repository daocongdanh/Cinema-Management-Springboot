package com.example.cinemamanagement.dtos;

import com.example.cinemamanagement.enums.RoomStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {

    @NotBlank(message = "roomName must be not blank")
    @JsonProperty("room_name")
    private String roomName;

    @JsonProperty("room_status")
    private RoomStatus roomStatus;
}
