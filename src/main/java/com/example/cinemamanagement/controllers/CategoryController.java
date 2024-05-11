package com.example.cinemamanagement.controllers;

import com.example.cinemamanagement.dtos.CategoryDTO;
import com.example.cinemamanagement.models.Category;
import com.example.cinemamanagement.responses.ResponseSuccess;
import com.example.cinemamanagement.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("")
    public ResponseEntity<ResponseSuccess> createCategory(
            @Valid @RequestBody CategoryDTO categoryDTO) {
        Category category = categoryService.createCategory(categoryDTO);
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Create category successfully")
                .status(HttpStatus.CREATED.value())
                .data(category)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseSuccess> getCategoryById(@PathVariable("id") long id){
        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Get category information successfully")
                .status(HttpStatus.OK.value())
                .data(category)
                .build());
    }

    @GetMapping("")
    public ResponseEntity<?> getAllCategories(){
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Get all category information successfully")
                .status(HttpStatus.OK.value())
                .data(categories)
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseSuccess> updateCategory(@PathVariable long id,
                                                          @Valid @RequestBody CategoryDTO categoryDTO){
        Category category = categoryService.updateCategory(id, categoryDTO);
        return ResponseEntity.ok().body(ResponseSuccess.builder()
                .message("Update category successfully")
                .status(HttpStatus.ACCEPTED.value())
                .data(category)
                .build());
    }

}
