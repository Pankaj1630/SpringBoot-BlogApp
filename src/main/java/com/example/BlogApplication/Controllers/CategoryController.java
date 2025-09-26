package com.example.BlogApplication.Controllers;

import com.example.BlogApplication.Entities.Category;
import com.example.BlogApplication.Payloads.ApiResponse;
import com.example.BlogApplication.Payloads.CategoryDTO;
import com.example.BlogApplication.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/createCategory")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO)
    {
        CategoryDTO createCategory = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(createCategory, HttpStatus.CREATED);
    }

    @PutMapping ("/updateCategory/{catID}")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable Integer catID)
    {
        CategoryDTO updateCategory = categoryService.updateCategory(categoryDTO, catID);
        return new ResponseEntity<>(updateCategory,HttpStatus.OK);
    }

    @DeleteMapping("/deleteCategory/{catID}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catID)
    {
        categoryService.deleteCategory(catID);
        return new ResponseEntity<>(new ApiResponse("Category deleted successfully", true), HttpStatus.OK);
    }

    @GetMapping("/getAllCategory")
    public ResponseEntity<List<CategoryDTO>> getCategory()
    {
        List<CategoryDTO> allCategory = categoryService.getAllCategory();
        return ResponseEntity.ok(allCategory);
    }

    @GetMapping("/getCategoryById/{userID}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Integer userID)
    {
        return ResponseEntity.ok(categoryService.getCategoryByID(userID));
    }
}
