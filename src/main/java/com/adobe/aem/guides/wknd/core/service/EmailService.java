package com.adobe.aem.guides.wknd.core.service;

public interface EmailService {
    void sendEmail(String subject, String message)throws Exception;

}

