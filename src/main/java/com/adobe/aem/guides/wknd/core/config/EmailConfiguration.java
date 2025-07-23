package com.adobe.aem.guides.wknd.core.config;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Email Service Configuration",description = "Email Setting For SMTP Server")
public @interface EmailConfiguration {

@AttributeDefinition(name = "SMTP Host",description = "SMTP server hostname")
String smtpHost() default "smtp.yourdomain.com";

    @AttributeDefinition(name = "SMTP Port",description = "SMTP Port")
    int smtpPort() default 587;

    @AttributeDefinition(name = "From Address",description = "Form of Address")
    String fromAddress() default "no-reply@example.com";

    @AttributeDefinition(name = "Sender Email",description = "Email Sender")
    String sender() default "Shraddha.Erande@coforge.com";

    @AttributeDefinition(name = "Recipient Email",description = " Email Recipient")
    String recipient() default "Chetan.Sonawane@coforge.com";

//    @AttributeDefinition(name = "Username")
//    String username();
//
//    @AttributeDefinition(name = "Password")
//    String password();
}



