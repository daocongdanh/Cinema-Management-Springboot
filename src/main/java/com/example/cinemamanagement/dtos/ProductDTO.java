package com.example.cinemamanagement.dtos;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    @NotBlank(message = "productName must be not blank")
    private String productName;

    @NotNull(message = "price must be not null")
    @Min(value = 1, message="Price must be greater than or equal to 1")
    private double price;

    @NotNull(message = "unitStock must be not null")
    @Min(value = 1, message="unitStock must be greater than or equal to 1")
    private int unitStock;

    @NotNull(message = "status must be not null")
    private boolean status;

    @NotNull(message = "categoryId must be not null")
    private Long categoryId;

    private MultipartFile image;
}
