package com.example.demo.service.impl;


import com.example.demo.dto.*;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import com.example.demo.entity.UserProfile;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserProfileRepository;
import com.example.demo.service.PostService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserProfileRepository userRepository;
    private final CommentRepository commentRepository;

    @Override
    public String createPost(PostCreateRequest request) {

        UserProfile userProfile = userRepository.findByUseId(Long.valueOf(request.getUserID()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User does not exist"));

        Post post = new Post();
        post.setPostBody(request.getPostBody());
        post.setUserProfile(userProfile);
        post.setCreatedDate(LocalDateTime.now());
        post.setUpdatedDate(LocalDateTime.now());
        postRepository.save(post);
        return "Post created successfully";
    }

    @Override
    public String updatePost(PostUpdateRequest request) {
        postRepository.findByPostID(request.getPostID()).ifPresentOrElse(post -> {
            post.setPostBody(request.getPostBody());
            post.setUpdatedDate(LocalDateTime.now());
            postRepository.save(post);
        }, () -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post does not exist");
        });
        return "Post edited successfully";
    }


    @Override
    public List<PostResponse> getAllPosts() {
        return postRepository.findAllByOrderByCreatedDateDesc().stream().map(post ->
                PostResponse.builder().postID(post.getPostID())
                        .postBody(post.getPostBody())
                        .date(post.getCreatedDate().toLocalDate())
                        .comments(mapComment(post))
                        .build()).toList();
    }

    @Override
    @Transactional
    public String deletePost(long postId) {
        postRepository.findByPostID(postId).ifPresentOrElse(post -> {
            postRepository.deleteByPostID(postId);
        }, () -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post does not exist");
        });
        return "Post deleted";
    }

    @Override
    public PostResponse getPostById(Long postID) {
        Post post = postRepository.findByPostID(postID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post does not exist"));
        return PostResponse.builder().postBody(post.getPostBody())
                .postID(postID)
                .date(post.getCreatedDate().toLocalDate())
                .comments(mapComment(post))
                .build();
    }

    private List<CommentResponse> mapComment(Post post) {
        List<Comment> comments = commentRepository.findByPost_PostID(post.getPostID());
        return comments.stream().map(comment ->
                CommentResponse.builder().commentBody(comment.getCommentBody())
                        .commentID(comment.getCommentID())
                        .commentCreator(CommentCreator.builder()
                                .name(comment.getUserProfile().getName())
                                .userID(comment.getUserProfile().getUseId())
                                .build())
                        .build()

        ).toList();
    }
}
