package com.example.cinemamanagement.responses;

import com.example.cinemamanagement.models.Category;

import com.example.cinemamanagement.models.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {

    private Long id;

    @JsonProperty("product_name")
    private String productName;

    private double price;

    @JsonProperty("unit_stock")
    private int unitStock;

    private String image;

    private boolean status;

    @JsonProperty("category_name")
    private String categoryName;

    public static ProductResponse fromProduct(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .price(product.getPrice())
                .unitStock(product.getUnitStock())
                .image(product.getImage())
                .status(product.isStatus())
                .categoryName(product.getCategory().getCategoryName())
                .build();
    }
}
