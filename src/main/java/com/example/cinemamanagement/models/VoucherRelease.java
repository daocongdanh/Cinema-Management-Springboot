package com.example.cinemamanagement.models;

import com.example.cinemamanagement.enums.ObjectType;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "voucher_release")
public class VoucherRelease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "release_name")
    private String realeaseName;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "finish_date")
    private LocalDate finishDate;

    @Column(name = "price")
    private double price;

    @Column(name = "min_price")
    private double minPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "object_type")
    private ObjectType objectType;
}
