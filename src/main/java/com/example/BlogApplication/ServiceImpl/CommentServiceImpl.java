package com.example.BlogApplication.ServiceImpl;

import com.example.BlogApplication.Entities.Comments;
import com.example.BlogApplication.Entities.Post;
import com.example.BlogApplication.Exception.ResourceNotFoundException;
import com.example.BlogApplication.Payloads.CommentDTO;
import com.example.BlogApplication.Payloads.PostDTO;
import com.example.BlogApplication.Repositories.CommentRepo;
import com.example.BlogApplication.Repositories.PostRepo;
import com.example.BlogApplication.Services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDTO createComment(CommentDTO commentDTO, Integer id) {
        Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "ID", id));
        Comments comment = modelMapper.map(commentDTO, Comments.class);
        comment.setPost(post);
        commentRepo.save(comment);
        return modelMapper.map(comment, CommentDTO.class);
    }

    @Override
    public void deleteComment(Integer id) {
        Comments comments = commentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment", "ID", id));
        commentRepo.delete(comments);
    }
}
