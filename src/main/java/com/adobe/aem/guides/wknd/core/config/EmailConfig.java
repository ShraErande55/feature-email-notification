package com.adobe.aem.guides.wknd.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Email config", description = "Configuration for sending emails")

public @interface EmailConfig {

    @AttributeDefinition(name = "Sender Email", description = "Email address used as sender")
    String senderEmail() default "no-reply@example.com";

    @AttributeDefinition(name = "Recipient Email", description = "Default recipient email address")
    String recipientEmail() default "shraddha@example.com";

    @AttributeDefinition(name = "Email Subject", description = "Default subject for the email")
    String emailSubject() default "Test Email Notification";

    @AttributeDefinition(name = "Email Content", description = "Default content for the email")
    String emailContent() default "Hi Team, I am sending mail for your reference.";
}


