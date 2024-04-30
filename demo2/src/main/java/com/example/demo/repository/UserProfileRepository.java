package com.example.demo.repository;

import com.example.demo.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    Optional<UserProfile> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<UserProfile> findByUseId(Long useId);
}