package com.example.demo.service.impl;

import com.example.demo.dto.CommentCreator;
import com.example.demo.dto.CommentResponse;
import com.example.demo.dto.CommentCreateRequest;
import com.example.demo.dto.CommentUpdateRequest;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import com.example.demo.entity.UserProfile;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserProfileRepository;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Component
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Override
    public String createComment(CommentCreateRequest request) {
        Post post = postRepository.findByPostID(request.getPostID())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post does not exist"));

        UserProfile userProfile = userProfileRepository.findByUseId(request.getUserID())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User does not exist"));
        commentRepository.save(
                Comment.builder().commentBody(request.getCommentBody())
                        .post(post)
                        .userProfile(userProfile)
                        .createdDate(LocalDateTime.now())
                        .updatedDate(LocalDateTime.now())
                        .build());
        return "Comment created successfully";
    }

    @Override
    public String updateComment(CommentUpdateRequest request) {
        commentRepository.findById(request.getCommentID())
                .ifPresentOrElse(comment -> {
                    comment.setCommentBody(request.getCommentBody());
                    commentRepository.save(comment);
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Comment does not exist");
                });
        return "Comment edited successfully";
    }

    @Override
    public CommentResponse getCommentById(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Comment does not exist"));

        return CommentResponse.builder().commentBody(comment.getCommentBody())
                .commentID(commentId)
                .commentCreator(CommentCreator.builder()
                        .name(comment.getUserProfile().getName())
                        .userID(comment.getUserProfile().getUseId())
                        .build())
                .build();
    }

    @Override
    public String deleteComment(long commentId) {
        commentRepository.findById(commentId)
                .ifPresentOrElse(comment -> commentRepository.deleteById(commentId), () -> {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Comment does not exist");
                });
        return "Comment deleted";
    }
}
