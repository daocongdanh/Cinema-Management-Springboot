package com.example.cinemamanagement.controllers;

import com.example.cinemamanagement.dtos.ProductDTO;
import com.example.cinemamanagement.responses.ProductResponse;
import com.example.cinemamanagement.responses.ResponseSuccess;
import com.example.cinemamanagement.services.ProductService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseSuccess> createProduct(@ModelAttribute @Valid ProductDTO productDTO){
        ProductResponse productResponse = productService.createProduct(productDTO);
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Create product successfully")
                .status(HttpStatus.CREATED.value())
                .data(productResponse)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseSuccess> getProductById(@PathVariable("id") long id){
        ProductResponse productResponse = productService.getProductById(id);
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Get product information successfully")
                .status(HttpStatus.OK.value())
                .data(productResponse)
                .build());
    }

    @GetMapping("")
    public ResponseEntity<ResponseSuccess> getAllProducts(){
        List<ProductResponse> productResponses = productService.getAllProducts();
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Get all product information successfully")
                .status(HttpStatus.OK.value())
                .data(productResponses)
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseSuccess> updateProduct(@PathVariable("id") long id,
                                                         @ModelAttribute @Valid ProductDTO productDTO){
        ProductResponse productResponse = productService.updateProduct(id,productDTO);
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Update product successfully")
                .status(HttpStatus.ACCEPTED.value())
                .data(productResponse)
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseSuccess> deleteProduct(@PathVariable("id") long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Delete product successfully")
                .status(HttpStatus.NO_CONTENT.value())
                .build());
    }
}
