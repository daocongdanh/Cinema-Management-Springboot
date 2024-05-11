package com.example.cinemamanagement.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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

    @Transient
    private final double VAT = 0.1;

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

    public double calcProductBill(){
        return productBills == null ? 0 : productBills.stream().mapToDouble(ProductBill::calcTotal).sum();
    }
    public double calcTicketBill(){
        return tickets.stream().mapToDouble(Ticket::calcTotal).sum();
    }

    public double getTotal() {
        return (calcProductBill() + calcTicketBill())*(1+VAT)
                - ((voucher == null) ? 0 : (voucher.getVoucherRelease().getPrice()));
    }
}
