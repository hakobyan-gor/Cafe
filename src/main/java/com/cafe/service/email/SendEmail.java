package com.cafe.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class SendEmail {

    @Lazy
    private final JavaMailSender javaMailSender;

    @Autowired
    public SendEmail(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendCodeToEmail(String email, String text, String subject) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject(subject);
        msg.setText(text);
        javaMailSender.send(msg);
    }

}