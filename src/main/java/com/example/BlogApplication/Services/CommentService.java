package com.example.BlogApplication.Services;

import com.example.BlogApplication.Payloads.CommentDTO;

public interface CommentService {
    CommentDTO createComment(CommentDTO commentDTO, Integer id);
    void deleteComment(Integer id);
}
