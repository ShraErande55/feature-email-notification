package com.adobe.aem.guides.wknd.core.servlet;
import com.adobe.aem.guides.wknd.core.config.EmailConfiguration;
import com.adobe.aem.guides.wknd.core.service.EmailService;
import com.day.cq.mailer.MessageGateway;
import com.day.cq.mailer.MessageGatewayService;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;

import javax.servlet.Servlet;
import java.io.IOException;

@Component(immediate = true,service = Servlet.class, property = {
//                "sling.servlet.paths=/bin/sendEmail",
//                  "sling.servlet.methods=POST"

Constants.SERVICE_DESCRIPTION + "=Manage Dynamic Site",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.methods=" +HttpConstants.METHOD_POST,
        "sling.servlet.resourceTypes=bin/email/service",
        "sling.servlet.extension=json"

       })
@Designate(ocd = EmailConfiguration.class)
public class EmailServlet extends SlingAllMethodsServlet {

    @Reference
    private MessageGatewayService messageGatewayService;

    @Reference
    EmailService emailService;
    private EmailConfiguration config;

    @Activate
    @Modified
    protected void activate(EmailConfiguration config) {
        this.config = config;
    }

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws IOException {
//        String subject = request.getParameter("subject");
//        String message = request.getParameter("message");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String description = request.getParameter("description");

//        if (firstname == null || lastname == null || description == null) {
//            response.getWriter().write("Missing required fields.");
//            return;
//        }

        String subject = "New Form Submission from " + firstname + " " + lastname;
        String message = "First Name: " + firstname + "\n"
                + "Last Name: " + lastname + "\n"
                + "Description: " + description;

        try {
            emailService.sendEmail(subject, message);
            response.setStatus(SlingHttpServletResponse.SC_OK);
            response.getWriter().write("Email sent successfully!");
        } catch (Exception e) {
            response.setStatus(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Failed to send email: " + e.getMessage());


        }
    }
}


