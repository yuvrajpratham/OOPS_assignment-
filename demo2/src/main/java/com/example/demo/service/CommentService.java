package com.example.demo.service;

import com.example.demo.dto.CommentResponse;
import com.example.demo.dto.CommentCreateRequest;
import com.example.demo.dto.CommentUpdateRequest;

public interface CommentService {

    String createComment(CommentCreateRequest request);

    String updateComment(CommentUpdateRequest request);

    CommentResponse getCommentById(Long commentId);

    String deleteComment(long commentId);
}
