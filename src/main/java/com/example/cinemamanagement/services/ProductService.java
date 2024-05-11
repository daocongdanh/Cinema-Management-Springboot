package com.example.cinemamanagement.services;

import com.example.cinemamanagement.dtos.ProductDTO;
import com.example.cinemamanagement.responses.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductDTO productDTO);
    ProductResponse getProductById(long id);
    List<ProductResponse> getAllProducts();
    ProductResponse updateProduct(long id, ProductDTO productDTO);
    void deleteProduct(long id);
}
