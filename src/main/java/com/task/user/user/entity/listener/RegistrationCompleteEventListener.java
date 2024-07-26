package com.task.user.user.entity.listener;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.task.user.user.entity.EmailEntity;
import com.task.user.user.entity.User;
import com.task.user.user.event.RegistrationCompleteEvent;
import com.task.user.user.service.EmailServiceImpl;
import com.task.user.user.service.UserService;



@Component
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    
    private static final Logger log = LoggerFactory.getLogger(RegistrationCompleteEventListener.class);

    @Autowired
    private UserService userService;

   @Autowired
   private EmailServiceImpl emailService;
   private EmailEntity email;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // Create verification token for user with link
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(user, token);

        // Construct the verification URL
        String verificationUrl = event.getApplicationUrl() + "/verify?token=" + token;

        // Send email to user
        String  msg=("Click the link to verify your account: {} " + verificationUrl);
        String receptient= user.getEmail();
        String status= emailService.sendSimpleMail(receptient,msg);
        log.info(status);
        log.info("Click the link to verify your account: {}", verificationUrl);

    }
}
