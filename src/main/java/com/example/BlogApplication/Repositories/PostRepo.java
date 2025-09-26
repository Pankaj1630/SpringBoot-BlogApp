package com.example.BlogApplication.Repositories;

import com.example.BlogApplication.Entities.Category;
import com.example.BlogApplication.Entities.Post;
import com.example.BlogApplication.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    List<Post> findByTitleContaining(String title);
}
