package com.example.BlogApplication.Repositories;

import com.example.BlogApplication.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
