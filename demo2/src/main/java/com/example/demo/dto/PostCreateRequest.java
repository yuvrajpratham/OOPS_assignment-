package com.example.demo.dto;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PostCreateRequest {
    private String postBody;
    private int userID;
}
