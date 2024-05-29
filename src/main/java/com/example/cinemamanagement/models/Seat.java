package com.example.cinemamanagement.models;

import com.example.cinemamanagement.enums.SeatType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "seat")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Enumerated(EnumType.STRING)
    @Column(name = "seat_type")
    private SeatType seatType;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    public int getPricePercent(){
        return seatType == SeatType.VIP ? 20 : 0;
    }

}
