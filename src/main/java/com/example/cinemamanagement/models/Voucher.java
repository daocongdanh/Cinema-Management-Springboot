package com.example.cinemamanagement.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "voucher")
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "status")
    private boolean status;

    @Column(name = "used_at")
    private LocalDateTime usedAt;

    @Column(name = "released_at")
    private LocalDateTime releasedAt;

    @ManyToOne
    @JoinColumn(name = "voucher_release_id", nullable = false)
    private VoucherRelease voucherRelease;
}
