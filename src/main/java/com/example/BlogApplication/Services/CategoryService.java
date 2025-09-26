package com.example.BlogApplication.Services;

import com.example.BlogApplication.Payloads.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(CategoryDTO categoryDTO,Integer id);
    List<CategoryDTO> getAllCategory();
    CategoryDTO getCategoryByID(Integer id);
    void deleteCategory(Integer id);
}
