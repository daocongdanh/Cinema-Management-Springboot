package com.example.cinemamanagement.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bill")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "total_price")
    private double totalPrice;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductBill> productBills;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ticket> tickets;

    @ManyToOne
    @JoinColumn(name = "voucher_id", nullable = true)
    private Voucher voucher;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public void addProductBill(ProductBill productBill){
        if(productBills == null)
            productBills = new ArrayList<>();
        productBills.add(productBill);
        productBill.setBill(this);
    }

    public void addTicket(Ticket ticket){
        if(tickets == null)
            tickets = new ArrayList<>();
        tickets.add(ticket);
        ticket.setBill(this);
    }
}
