package com.hnu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailServiceInterface {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("3024895118@qq.com");  // 发件人的邮箱
        message.setTo(to);  // 收件人的邮箱
        message.setSubject(subject);  // 邮件标题
        message.setText(text);  // 邮件内容

        mailSender.send(message);
    }
}
