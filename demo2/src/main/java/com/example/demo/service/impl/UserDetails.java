package com.example.demo.service.impl;

import com.example.demo.entity.UserProfile;
import com.example.demo.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetails implements UserDetailsService {

    @Autowired
    private UserProfileRepository userRepository;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserProfile userProfile = userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User does not exist"));
        return new User(userProfile.getEmail(), userProfile.getPassword(), new ArrayList<>());
    }
}
