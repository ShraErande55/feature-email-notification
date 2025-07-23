package com.adobe.aem.guides.wknd.core.service.Impl;

import com.adobe.aem.guides.wknd.core.config.EmailConfiguration;
import com.adobe.aem.guides.wknd.core.service.EmailService;
import com.day.cq.mailer.MessageGateway;
import com.day.cq.mailer.MessageGatewayService;
import org.apache.commons.mail.HtmlEmail;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;

@Component(service = EmailService.class,immediate = true)
@Designate(ocd = EmailConfiguration.class)
public class EmailServiceImpl implements EmailService {

   private  EmailConfiguration config;

    @Reference
    private MessageGatewayService messageGatewayService;

    @Activate
    @Modified
    protected void activate(EmailConfiguration config) {
        this.config = config;
    }

    @Override
    public void sendEmail(String subject, String message) throws Exception {

        try {
            HtmlEmail email = new HtmlEmail();
            email.setHostName(config.smtpHost());
            email.setSmtpPort(config.smtpPort());
            email.setStartTLSEnabled(true);
            email.setFrom(config.fromAddress());
            email.setSubject(subject);
            email.setMsg(message);
            email.addTo(config.recipient());
            email.send();
//        email.setAuthentication(config.username(), config.password());
            MessageGateway<HtmlEmail> gateway = messageGatewayService.getGateway(HtmlEmail.class);
            if (gateway != null) {
                gateway.send(email);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}
