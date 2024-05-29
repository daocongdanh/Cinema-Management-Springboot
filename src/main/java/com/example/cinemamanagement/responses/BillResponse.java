package com.example.cinemamanagement.responses;

import com.example.cinemamanagement.models.Bill;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillResponse {

    private Long id;

    @JsonProperty("created_at")
    private LocalDate createdAt;

    @JsonProperty("total_price")
    private double totalPrice;

    @JsonProperty("product_bills")
    private List<ProductBillResponse> productBills;

    private List<TicketResponse> tickets;

    @JsonProperty("voucher_id")
    private Long voucherId;

    @JsonProperty("user_id")
    private Long userId;

    public static BillResponse fromBill(Bill bill){
        return BillResponse.builder()
                .id(bill.getId())
                .createdAt(bill.getCreatedAt())
                .totalPrice(bill.getTotalPrice())
                .productBills(
                        bill.getProductBills().stream()
                                .map(ProductBillResponse::fromProductBill)
                                .toList()
                )
                .tickets(
                        bill.getTickets().stream()
                                .map(TicketResponse::fromTicket)
                                .toList()
                )
                .voucherId(bill.getVoucher() == null ? null : bill.getVoucher().getId())
                .userId(bill.getUser().getId())
                .build();
    }
}
