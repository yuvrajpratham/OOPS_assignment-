package com.example.demo.controller;


import com.example.demo.dto.CommentResponse;
import com.example.demo.dto.CommentCreateRequest;
import com.example.demo.dto.CommentUpdateRequest;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public String addComment(@RequestBody CommentCreateRequest comment) {
        return commentService.createComment(comment);
    }

    @PatchMapping
    public String updateComment(@RequestBody CommentUpdateRequest comment) {
        return commentService.updateComment(comment);
    }

    @DeleteMapping
    public String deleteComment(@RequestParam Long commentID) {
        return commentService.deleteComment(commentID);
    }

    @GetMapping
    public CommentResponse getComments(@RequestParam Long commentID) {
        return commentService.getCommentById(commentID);
    }

}
