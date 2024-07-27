package com.task.user.user.service;

import java.util.Optional;
import java.util.List;
import com.task.user.user.entity.User;
import com.task.user.user.entity.VerificationToken;
import com.task.user.user.models.UserModel;


public interface  UserService {

    User registerUser(UserModel userModel);

    void saveVerificationTokenForUser(User user, String token);
    String validateVerificationToken(String token);
    VerificationToken generateNewVerificationToken(String oldToken);

    User findUserByEmail(String email);

    void createPasswordResetTokenForUser(User user, String token);

    String validatePasswordResetToken(String token);

    Optional<User> getUserByPasswordResetToken(String token);

    void changePassword(User user, String newPassword);

    boolean checkIfValidOldPassword(User user, String oldPassword);

    public List<User> getAllUsers();



    
}
