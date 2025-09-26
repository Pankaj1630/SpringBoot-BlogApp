package com.example.BlogApplication.Repositories;

import com.example.BlogApplication.Entities.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comments,Integer> {
}
