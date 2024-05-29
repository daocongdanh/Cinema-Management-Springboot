package com.example.cinemamanagement.dtos;

import com.example.cinemamanagement.models.ProductBill;
import com.example.cinemamanagement.models.Ticket;
import com.example.cinemamanagement.models.User;
import com.example.cinemamanagement.models.Voucher;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillDTO {

    @JsonProperty("created_at")
    @JsonFormat(pattern = "dd-MM-yyy")
    private LocalDate createdAt;

    @JsonProperty("total_price")
    private double totalPrice;

    @JsonProperty("product_bills")
    private List<ProductBillDTO> productBills;

    @JsonProperty("tickets")
    private List<TicketDTO> tickets;

    @JsonProperty("voucher_id")
    private Long voucherId;

    @JsonProperty("user_id")
    private Long userId;
}
