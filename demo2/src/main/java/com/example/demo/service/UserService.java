package com.example.demo.service;


import com.example.demo.dto.LoginModel;
import com.example.demo.dto.SignupModel;
import com.example.demo.dto.UserProfileModel;

import java.util.List;

public interface UserService {

    String loginUser(LoginModel loginModel);

    String sigUpUser(SignupModel signupModel);

    UserProfileModel getPortalUserById(Integer userID);

    List<UserProfileModel> getAllUsers();
}
