package com.example.demo.controller;

import com.example.demo.dto.LoginModel;
import com.example.demo.dto.PostResponse;
import com.example.demo.dto.SignupModel;
import com.example.demo.dto.UserProfileModel;
import com.example.demo.service.PostService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @PostMapping("/login")
    public String loginUser(@RequestBody LoginModel loginModel)
    {
        return userService.loginUser(loginModel);
    }

    @PostMapping("/signup")
    public String signupUser(@RequestBody SignupModel signupModel) {
        return userService.sigUpUser(signupModel);
    }

    @GetMapping("/user")
    public UserProfileModel getUserProfileById(@RequestParam Integer userID) {
        return userService.getPortalUserById(userID);
    }

    @GetMapping("users")
    public List<UserProfileModel> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping
    public List<PostResponse> getAllPosts() {
        return postService.getAllPosts();
    }
}
