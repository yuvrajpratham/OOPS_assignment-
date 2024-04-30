package com.example.demo.dto;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostUpdateRequest {
    private int postID;
    private String postBody;
}
