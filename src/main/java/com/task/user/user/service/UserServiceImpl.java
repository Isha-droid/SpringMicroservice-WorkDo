package com.task.user.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.task.user.user.entity.User;
import com.task.user.user.entity.VerificationToken;
import com.task.user.user.models.UserModel;
import com.task.user.user.repository.UserRepository;
import com.task.user.user.repository.VerificationTokenRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(UserModel userModel) {
        User user = new User();
        user.setEmail(userModel.getEmail());
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(userModel.getPassword())); // Encode the password
        user.setEnabled(false); // Enable user by default

        return userRepository.save(user);
    }

    @Override
    public void saveVerificationTokenForUser(User user, String token) {
        VerificationToken verificationToken = new VerificationToken(user, token);
        tokenRepository.save(verificationToken);
    }
}
