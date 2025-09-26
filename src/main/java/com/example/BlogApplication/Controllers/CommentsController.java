package com.example.BlogApplication.Controllers;

import com.example.BlogApplication.Payloads.ApiResponse;
import com.example.BlogApplication.Payloads.CommentDTO;
import com.example.BlogApplication.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentsController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{id}/comments")
    public ResponseEntity<CommentDTO> createComment(@RequestParam CommentDTO commentDTO,
                                                    @PathVariable Integer id) {
        CommentDTO comment = commentService.createComment(commentDTO, id);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @DeleteMapping("/posts/{id}/comments")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>(new ApiResponse("Comment deleted succesfully", true), HttpStatus.OK);
    }
}
