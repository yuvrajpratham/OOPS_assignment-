package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentResponse {

    private Long commentID;
    private String commentBody;
    private CommentCreator commentCreator;
}
