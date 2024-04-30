package com.example.demo.service;

import com.example.demo.dto.PostResponse;
import com.example.demo.dto.PostCreateRequest;
import com.example.demo.dto.PostUpdateRequest;

import java.util.List;

public interface PostService {

    String createPost(PostCreateRequest request);

    String updatePost(PostUpdateRequest request);

    List<PostResponse> getAllPosts();

    String deletePost(long postId);

    PostResponse getPostById(Long postID);
}
