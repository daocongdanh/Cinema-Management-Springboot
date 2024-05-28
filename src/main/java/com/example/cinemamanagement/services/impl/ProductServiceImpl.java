package com.example.cinemamanagement.services.impl;

import com.example.cinemamanagement.dtos.ProductDTO;
import com.example.cinemamanagement.exceptions.DuplicateValueException;
import com.example.cinemamanagement.exceptions.ResourceNotFoundException;
import com.example.cinemamanagement.models.Category;
import com.example.cinemamanagement.models.Product;
import com.example.cinemamanagement.repositories.CategoryRepository;
import com.example.cinemamanagement.repositories.ProductRepository;
import com.example.cinemamanagement.responses.ProductResponse;
import com.example.cinemamanagement.services.CategoryService;
import com.example.cinemamanagement.services.ProductService;
import com.example.cinemamanagement.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final FileUtil fileUtil;
    @Override
    @Transactional
    public ProductResponse createProduct(ProductDTO productDTO) {
        if(productDTO.getImage() == null)
            throw new RuntimeException("image must be not blank");
        if(productRepository.existsByProductName(productDTO.getProductName()))
            throw new DuplicateValueException("Product name already exists");

        Category category = categoryService.getCategoryById(productDTO.getCategoryId());
        String image = fileUtil.createFile(productDTO.getImage());
        Product product = productRepository.save(Product.builder()
                .productName(productDTO.getProductName())
                .price(productDTO.getPrice())
                .unitStock(productDTO.getUnitStock())
                .image(image)
                .status(productDTO.isStatus())
                .category(category)
                .build());
        return ProductResponse.fromProduct(product);
    }

    @Override
    public ProductResponse getProductById(long id) {
        Product product = productRepository.findById(id).orElse(null);
        if(product == null)
            throw new ResourceNotFoundException("Product not found");
        return ProductResponse.fromProduct(product);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductResponse::fromProduct)
                .toList();
    }

    @Override
    public List<ProductResponse> getProductByCategory(long cid) {
        Category category = categoryService.getCategoryById(cid);
        return productRepository.findByCategory(category).stream()
                .map(ProductResponse::fromProduct)
                .toList();
    }

    @Override
    @Transactional
    public ProductResponse updateProduct(long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        Category category = categoryService.getCategoryById(productDTO.getCategoryId());
        if(!product.getProductName().equals(productDTO.getProductName())
                && productRepository.existsByProductName(productDTO.getProductName())){
            throw new DuplicateValueException("Product name already exists");
        }
        product.setProductName(productDTO.getProductName());
        product.setPrice(productDTO.getPrice());
        product.setUnitStock(productDTO.getUnitStock());
        product.setStatus(productDTO.isStatus());
        product.setCategory(category);
        if(!productDTO.getImage().getOriginalFilename().equals("")){ // Cập nhật lại image
            String image = fileUtil.updateFile(productDTO.getImage(), product.getImage());
            product.setImage(image);
        }
        return ProductResponse.fromProduct(productRepository.save(product));
    }

    @Override
    public void deleteProduct(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        product.setStatus(false);
        productRepository.save(product);
    }
}
