package com.projectmanagement.projectmanagement.service;

public interface EmailService {

    void sendEmailWithToken(String userEmail, String link) throws Exception;
    
}
