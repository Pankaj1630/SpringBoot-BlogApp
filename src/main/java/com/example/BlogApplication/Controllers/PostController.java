package com.example.BlogApplication.Controllers;

import com.example.BlogApplication.Services.FileService;
import com.example.BlogApplication.Utils.AppConstant;
import com.example.BlogApplication.Payloads.ApiResponse;
import com.example.BlogApplication.Payloads.PostDTO;
import com.example.BlogApplication.Payloads.PostResponse;
import com.example.BlogApplication.Services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping("/createPost/user/{userId}/category/{catId}")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer catId) {
        PostDTO post = postService.createPost(postDTO, userId, catId);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @GetMapping("/getPostByUser/user/{userId}")
    public ResponseEntity<List<PostDTO>> getPostByUserId(@PathVariable Integer userId) {
        List<PostDTO> allPostByUser = postService.getAllPostByUser(userId);
        return new ResponseEntity<>(allPostByUser, HttpStatus.OK);
    }

    @GetMapping("/getPostByCategory/category/{catId}")
    public ResponseEntity<List<PostDTO>> getPostCatId(@PathVariable Integer catId) {
        List<PostDTO> allPostByCategory = postService.getAllPostByCategory(catId);
        return new ResponseEntity<>(allPostByCategory, HttpStatus.OK);
    }

    @GetMapping("/getAllPost")
    public ResponseEntity<PostResponse> getAllPost(@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
                                                   @RequestParam(value = "pagesize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pagesize,
                                                   @RequestParam(value = "sortBy", defaultValue = AppConstant.SORT_BY, required = false) String sortBy,
                                                   @RequestParam(value = "sortDir", defaultValue = AppConstant.SORT_DIR, required = false) String sortDir) {
        PostResponse allPost = postService.getAllPost(pageNumber, pagesize, sortBy, sortDir);
        return new ResponseEntity<>(allPost, HttpStatus.OK);
    }

    @GetMapping("/getPostById/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId) {
        PostDTO postById = postService.getPostById(postId);
        return new ResponseEntity<>(postById, HttpStatus.OK);
    }

    @DeleteMapping("deletePost/{postId}")
    public ApiResponse deletePostById(@PathVariable Integer postId) {
        postService.deletePost(postId);
        return new ApiResponse("Post deleted sucessfully", true);
    }

    @PutMapping("/updatePost/{postId}")
    public ResponseEntity<PostDTO> updatepost(@RequestBody PostDTO postDTO, @PathVariable Integer postId) {
        PostDTO updatePost = postService.updatePost(postDTO, postId);
        return new ResponseEntity<>(updatePost, HttpStatus.OK);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<PostDTO>> searchByTitleContaining(@PathVariable String keyword) {
        List<PostDTO> dtoList = postService.searchPost(keyword);
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @PostMapping("/image/upload/{postId}")
    public ResponseEntity<PostDTO> uploadImage(@RequestParam("image") MultipartFile image,
                                               @PathVariable Integer postId) throws IOException {
        PostDTO postById = postService.getPostById(postId);
        String filename = fileService.uploadImage(path, image);

        postById.setImageName(filename);
        PostDTO updatePost = postService.updatePost(postById, postId);
        return new ResponseEntity<>(updatePost, HttpStatus.OK);
    }

    @GetMapping(value = "/image/download/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage (@PathVariable("imageName") String imageName,
                              HttpServletResponse response) throws IOException {
        InputStream resource = fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }

}
