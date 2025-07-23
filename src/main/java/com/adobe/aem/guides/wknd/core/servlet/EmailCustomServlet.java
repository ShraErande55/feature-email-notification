package com.adobe.aem.guides.wknd.core.servlet;
import com.adobe.aem.guides.wknd.core.config.EmailConfig;
import com.adobe.aem.guides.wknd.core.service.EmailConfigService;
import com.day.cq.mailer.MessageGateway;
import com.day.cq.mailer.MessageGatewayService;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class,immediate = true,
        property = {
                "sling.servlet.paths=/bin/customEmailSer",
                "sling.servlet.methods=Get"
        }
)
public class EmailCustomServlet extends SlingAllMethodsServlet {
//    @Reference
//    MessageGatewayService messageGatewayService;

private static  final  Logger logger = LoggerFactory.getLogger(EmailCustomServlet.class);
    @Reference
    private EmailConfigService emailConfigService;


    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
//        if (messageGatewayService !=null) {
//            MessageGateway<HtmlEmail> gateway = messageGatewayService.getGateway(HtmlEmail.class);
//        HtmlEmail htmlEmail = new HtmlEmail();
//            try {
//                htmlEmail.setFrom("no-reply@gmail.com");
//                htmlEmail.addTo("shraddhagunjal71@gmail.com");
//                htmlEmail.setSubject("Test Email Notification");
//                htmlEmail.setContent("Hi Team,I am sending mail for your reference","text/html");
//                gateway.send(htmlEmail);
//
//        response.getWriter().write("Custom Email sent successfully!");
//
//            } catch (EmailException e) {
////                throw new RuntimeException(e);
//            }
//        }
//    }
//}

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String description = request.getParameter("description");
        String mail = request.getParameter("mail");

        String message = "First Name: " + firstName + "<br/>" +
                "Last Name: " + lastName + "<br/>" +
                "Description: " + description;
//        logger.info("Custom Email Notification",emailConfig.recipientEmail());

        emailConfigService.sendEmail(mail, "New Submission Email Form", message);
        response.getWriter().write("Custom EMail Notification sent successfully!");



//        String to = request.getParameter("to");
//        String subject = request.getParameter("subject");
//        String content = request.getParameter("content");
//
//        emailConfigService.sendEmail(to, subject, content);
//
//        response.getWriter().write("Yeah Custom Mail sent successfully!");
    }
}
