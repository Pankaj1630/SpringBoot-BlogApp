package com.example.BlogApplication.Repositories;

import com.example.BlogApplication.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
