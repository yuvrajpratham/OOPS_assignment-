package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentUpdateRequest {

    private Long commentID;
    private String commentBody;
}
