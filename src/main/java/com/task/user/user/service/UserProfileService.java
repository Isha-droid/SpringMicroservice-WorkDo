package com.task.user.user.service;

import com.task.user.user.entity.User;
import com.task.user.user.repository.UserRepository;
import com.task.user.user.utility.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtility jwtUtility;

    public User getUserProfile(String token) {
        String email = jwtUtility.getUsernameFromToken(token);
        return userRepository.findByEmail(email);
    }
}
