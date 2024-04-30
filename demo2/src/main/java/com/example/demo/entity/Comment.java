package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentID;

    private String commentBody;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    @ManyToOne
    private Post post;

    @ManyToOne
    private UserProfile userProfile;
}
