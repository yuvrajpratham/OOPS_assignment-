package com.example.demo.controller;

import com.example.demo.dto.PostResponse;
import com.example.demo.dto.PostCreateRequest;
import com.example.demo.dto.PostUpdateRequest;
import com.example.demo.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@Validated
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostCreateRequest request) {
        return ResponseEntity.ok(postService.createPost(request));
    }

    @PatchMapping
    public String updatePost(@RequestBody @Valid PostUpdateRequest request) {
        return postService.updatePost(request);
    }

    @DeleteMapping
    public String deletePost(@RequestParam Long postID) {
        return postService.deletePost(postID);
    }

    @GetMapping
    public PostResponse getPost(@RequestParam Long postID) {
        return postService.getPostById(postID);
    }

}
