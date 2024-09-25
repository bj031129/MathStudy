package com.hnu.service;

public interface EmailServiceInterface {
    void sendEmail(String to, String subject, String text);
}
