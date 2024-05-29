package com.example.cinemamanagement.responses;

import com.example.cinemamanagement.models.ProductBill;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductBillResponse {

    private Long id;

    private int quantity;

    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("bill_id")
    private Long billId;

    public static ProductBillResponse fromProductBill(ProductBill productBill){
        return ProductBillResponse.builder()
                .id(productBill.getId())
                .quantity(productBill.getQuantity())
                .productId(productBill.getProduct().getId())
                .billId(productBill.getBill().getId())
                .build();
    }
}
