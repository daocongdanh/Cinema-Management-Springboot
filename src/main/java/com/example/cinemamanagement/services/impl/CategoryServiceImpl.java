package com.example.cinemamanagement.services.impl;

import com.example.cinemamanagement.dtos.CategoryDTO;
import com.example.cinemamanagement.exceptions.ResourceNotFoundException;
import com.example.cinemamanagement.exceptions.DuplicateValueException;
import com.example.cinemamanagement.models.Category;
import com.example.cinemamanagement.repositories.CategoryRepository;
import com.example.cinemamanagement.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    @Transactional
    public Category createCategory(CategoryDTO categoryDTO) {
        if(categoryRepository.existsByCategoryName(categoryDTO.getCategoryName())){
            throw new DuplicateValueException("Category name already exists");
        }
        else{
            Category category = Category
                    .builder()
                    .categoryName(categoryDTO.getCategoryName())
                    .build();
            return categoryRepository.save(category);
        }
    }

    @Override
    public Category getCategoryById(long id) {
        return categoryRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Category not found"));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional
    public Category updateCategory(long id, CategoryDTO categoryDTO) {
        Category category = getCategoryById(id);
        if(!category.getCategoryName().equals(categoryDTO.getCategoryName())
        && categoryRepository.existsByCategoryName(categoryDTO.getCategoryName())){
            throw new DuplicateValueException("Category name already exists");
        }
        category.setCategoryName(categoryDTO.getCategoryName());
        return categoryRepository.save(category);
    }

}
