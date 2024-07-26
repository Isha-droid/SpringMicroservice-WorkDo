package com.task.user.user.service;

import com.task.user.user.entity.User;
import com.task.user.user.models.UserModel;

public interface  UserService {

    User registerUser(UserModel userModel);

    void saveVerificationTokenForUser(User user, String token);
    String validateVerificationToken(String token);


    
}
