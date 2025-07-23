package com.adobe.aem.guides.wknd.core.service;

public interface EmailConfigService {

    void sendEmail(String to, String subject, String content);

}
