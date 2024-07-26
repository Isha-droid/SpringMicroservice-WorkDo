package com.task.user.user.service;


import org.springframework.stereotype.Service;
@Service
// Interface
public interface EmailService {
 
    // Method
    // To send a simple email
    String sendSimpleMail(String recepString, String msg);
 
    // Method
    // To send an email with attachment
}