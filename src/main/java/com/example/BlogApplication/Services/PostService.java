package com.example.BlogApplication.Services;

import com.example.BlogApplication.Entities.Category;
import com.example.BlogApplication.Entities.Post;
import com.example.BlogApplication.Entities.User;
import com.example.BlogApplication.Payloads.PostDTO;
import com.example.BlogApplication.Payloads.PostResponse;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postdto,Integer userId,Integer catId);
    PostDTO updatePost(PostDTO postDTO,Integer id);
    void deletePost(Integer id);
    PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);
    PostDTO getPostById(Integer id);
    List<PostDTO> getAllPostByCategory(Integer id);
    List<PostDTO> getAllPostByUser(Integer id);
    List<PostDTO> searchPost(String keyword);
}
