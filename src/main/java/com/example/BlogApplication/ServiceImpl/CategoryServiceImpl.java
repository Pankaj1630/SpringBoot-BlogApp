package com.example.BlogApplication.ServiceImpl;

import com.example.BlogApplication.Entities.Category;
import com.example.BlogApplication.Exception.ResourceNotFoundException;
import com.example.BlogApplication.Payloads.CategoryDTO;
import com.example.BlogApplication.Repositories.CategoryRepo;
import com.example.BlogApplication.Services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        return modelMapper.map(categoryRepo.save(category), CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer id) {
        Category category = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        category.setCatTitle(categoryDTO.getCatTitle());
        category.setCatDesc(categoryDTO.getCatDesc());
        return modelMapper.map(categoryRepo.save(category), CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        List<Category> allCategory = categoryRepo.findAll();
        return allCategory.stream().map(category -> modelMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());

    }

    @Override
    public CategoryDTO getCategoryByID(Integer id) {
        return modelMapper.map(categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id)), CategoryDTO.class);
    }

    @Override
    public void deleteCategory(Integer id) {
        Category category = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        categoryRepo.delete(category);
    }
}
