package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentCreator {

    private Long userID;
    private String name;
}
