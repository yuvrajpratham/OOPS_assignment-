package com.example.demo.service.impl;

import com.example.demo.dto.LoginModel;
import com.example.demo.dto.SignupModel;
import com.example.demo.dto.UserProfileModel;
import com.example.demo.entity.UserProfile;
import com.example.demo.repository.UserProfileRepository;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserProfileRepository profileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private UserProfileRepository userProfileRepository;


    @Override
    public String loginUser(LoginModel loginModel) {
        profileRepository.findByEmail(loginModel.getEmail()).ifPresentOrElse(portalUser -> {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginModel.getEmail(), loginModel.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }, () -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User does not exist");
        });
        return "Login Successful";
    }

    @Override
    public String sigUpUser(SignupModel signupModel) {
        profileRepository.findByEmail(signupModel.getEmail()).ifPresent(portalUser -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Forbidden, Account already exists");
        });
        UserProfile userProfile = objectMapper.convertValue(signupModel, UserProfile.class);
        userProfile.setPassword(passwordEncoder.encode(signupModel.getPassword()));
        profileRepository.save(userProfile);
        return "Account Creation Successful";
    }

    @Override
    public UserProfileModel getPortalUserById(Integer userID) {
        UserProfile userProfile = profileRepository.findByUseId(Long.valueOf(userID)).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User does not exist"));
        UserProfileModel userProfileModel = new UserProfileModel();
        userProfileModel.setUserID(userProfile.getUseId());
        userProfileModel.setName(userProfile.getName());
        userProfileModel.setEmail(userProfile.getEmail());
        return userProfileModel;
    }

    @Override
    public List<UserProfileModel> getAllUsers() {
        return userProfileRepository.findAll().stream().map(userProfile -> {
            UserProfileModel userProfileModel = new UserProfileModel();
            userProfileModel.setName(userProfile.getName());
            userProfileModel.setEmail(userProfile.getEmail());
            userProfileModel.setUserID(userProfile.getUseId());
            return userProfileModel;
        }).toList();
    }
}
