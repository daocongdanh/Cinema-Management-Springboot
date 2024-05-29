package com.example.cinemamanagement.responses;

import com.example.cinemamanagement.models.Ticket;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketResponse {

    private Long id;

    @JsonProperty("show_time_id")
    private Long showTimeId;

    @JsonProperty("seat_id")
    private Long seatId;

    @JsonProperty("bill_id")
    private Long billId;

    public static TicketResponse fromTicket(Ticket ticket){
        return TicketResponse.builder()
                .id(ticket.getId())
                .showTimeId(ticket.getShowTime().getId())
                .seatId(ticket.getSeat().getId())
                .billId(ticket.getBill().getId())
                .build();
    }
}
