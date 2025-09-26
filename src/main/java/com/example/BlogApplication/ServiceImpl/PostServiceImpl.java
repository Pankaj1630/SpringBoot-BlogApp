package com.example.BlogApplication.ServiceImpl;

import com.example.BlogApplication.Entities.Category;
import com.example.BlogApplication.Entities.Post;
import com.example.BlogApplication.Entities.User;
import com.example.BlogApplication.Exception.ResourceNotFoundException;
import com.example.BlogApplication.Payloads.PostDTO;
import com.example.BlogApplication.Payloads.PostResponse;
import com.example.BlogApplication.Repositories.CategoryRepo;
import com.example.BlogApplication.Repositories.PostRepo;
import com.example.BlogApplication.Repositories.UserRepository;
import com.example.BlogApplication.Services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDTO createPost(PostDTO postdto, Integer userId, Integer catId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        Category category = categoryRepo.findById(catId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", catId));
        Post post = modelMapper.map(postdto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        postRepo.save(post);
        return modelMapper.map(post, PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Integer id) {
        Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setImageName(postDTO.getImageName());
        postRepo.save(post);
        return modelMapper.map(post, PostDTO.class);
    }

    @Override
    public void deletePost(Integer id) {
        Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
        postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
        Sort sort = null;
        sort = sortDir.equalsIgnoreCase("ASC") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        PostResponse ps = new PostResponse();
        Pageable p = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> all = postRepo.findAll(p);
        List<Post> content = all.getContent();
        List<PostDTO> postDTOS = content.stream().map(post -> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
        ps.setContent(postDTOS);
        ps.setPageNumber(all.getNumber());
        ps.setPageSize(all.getNumber());
        ps.setPageNumber(all.getSize());
        ps.setTotalPages(all.getTotalPages());
        ps.setTotalElement(all.getNumberOfElements());
        ps.setLastPage(all.isLast());
        return ps;
    }

    @Override
    public PostDTO getPostById(Integer id) {
        Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
        return modelMapper.map(post, PostDTO.class);
    }

    @Override
    public List<PostDTO> getAllPostByCategory(Integer id) {
        Category category = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", id));
        List<Post> posts = postRepo.findByCategory(category);
        return posts.stream().map(post -> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> getAllPostByUser(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
        List<Post> posts = postRepo.findByUser(user);
        return posts.stream().map(post -> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> searchPost(String keyword) {
        List<Post> postList = postRepo.findByTitleContaining(keyword);
        return postList.stream().map(elem->modelMapper.map(elem,PostDTO.class)).collect(Collectors.toList());
    }
}
