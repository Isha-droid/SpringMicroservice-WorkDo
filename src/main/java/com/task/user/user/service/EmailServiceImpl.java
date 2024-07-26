package com.task.user.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private final String sender;

    public EmailServiceImpl(JavaMailSender javaMailSender, @Value("${spring.mail.username}") String sender) {
        this.javaMailSender = javaMailSender;
        this.sender = sender;
    }

    @Override
    public String sendSimpleMail(String recepString, String msg) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(recepString);
            mailMessage.setText(msg);
            mailMessage.setSubject("");

            javaMailSender.send(mailMessage);
            logger.info("Simple mail sent successfully to {}", recepString);
            return "Mail Sent Successfully...";
        } catch (Exception e) {
            logger.error("Error while sending mail to {}", recepString, e);
            return "Error while Sending Mail";
        }
    }

   
}
