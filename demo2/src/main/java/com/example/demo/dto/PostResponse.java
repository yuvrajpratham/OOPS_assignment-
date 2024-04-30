package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class PostResponse {
    private Long postID;
    private String postBody;
    private LocalDate date;
    private List<CommentResponse> comments;
}
