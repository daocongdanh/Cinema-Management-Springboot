package com.example.cinemamanagement.services;

import com.example.cinemamanagement.dtos.CategoryDTO;
import com.example.cinemamanagement.models.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(CategoryDTO categoryDTO);
    Category getCategoryById(long id);
    List<Category> getAllCategories();
    Category updateCategory(long id, CategoryDTO categoryDTO);
}
