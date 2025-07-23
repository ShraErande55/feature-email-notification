package com.adobe.aem.guides.wknd.core.service.Impl;




import com.adobe.aem.guides.wknd.core.config.EmailConfig;
import com.adobe.aem.guides.wknd.core.service.EmailConfigService;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import com.day.cq.mailer.MessageGateway;
import com.day.cq.mailer.MessageGatewayService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = EmailConfigService.class)
@Designate(ocd = EmailConfig.class)
public class EmailConfigServiceImpl implements EmailConfigService {

    private static final Logger logger = LoggerFactory.getLogger(EmailConfigServiceImpl.class);
    @Reference
    private MessageGatewayService messageGatewayService;

    private EmailConfig config;

    @Activate
    protected void activate(EmailConfig config) {
        this.config = config;
    }

    @Override
    public void sendEmail(String to, String subject, String content) {
        logger.info("Send Email");
        if (messageGatewayService != null) {
            MessageGateway<HtmlEmail> gateway = messageGatewayService.getGateway(HtmlEmail.class);
            HtmlEmail htmlEmail = new HtmlEmail();
            try {
                htmlEmail.setFrom(config.senderEmail());
                htmlEmail.addTo(to != null ? to : config.recipientEmail());
                htmlEmail.setSubject(subject != null ? subject : config.emailSubject());
                htmlEmail.setContent(content != null ? content : config.emailContent(), "text/html");
                gateway.send(htmlEmail);
            } catch (EmailException e) {
                e.printStackTrace(); // Replace with proper logging
            }
        }
    }
}

