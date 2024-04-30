package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentCreateRequest {

    private Long postID;
    private Long userID;
    private String commentBody;
}
