package com.task.user.user.controller;


import com.task.user.user.entity.User;
import com.task.user.user.service.UserProfileService;
import com.task.user.user.service.UserServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/profile")
    public User getUserProfile(@RequestHeader("Authorization") String token) {
        // Remove the "Bearer " prefix if it exists
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return userProfileService.getUserProfile(token);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
