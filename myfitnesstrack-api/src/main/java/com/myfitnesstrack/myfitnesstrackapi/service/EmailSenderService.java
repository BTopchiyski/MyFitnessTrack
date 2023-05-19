package com.myfitnesstrack.myfitnesstrackapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationEmail(String toEmail,
                                      String body,
                                      String subject) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("myfitnesstrackBG@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);
        System.out.println("Mail send...");
    }

}
