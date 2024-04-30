package com.example.demo.repository;

import com.example.demo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findByPostID(long postId);
    void deleteByPostID(long postId);
    List<Post> findAllByOrderByCreatedDateDesc();
}