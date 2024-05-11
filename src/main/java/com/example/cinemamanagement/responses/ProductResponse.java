package com.example.cinemamanagement.responses;

import com.example.cinemamanagement.models.Category;

import com.example.cinemamanagement.models.Product;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {

    private Long id;

    private String productName;

    private double price;

    private int unitStock;

    private String image;

    private boolean status;

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
