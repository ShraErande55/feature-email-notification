//package com.adobe.aem.guides.wknd.core.service.Impl;
//
//import com.day.cq.mailer.MessageGateway;
//import org.apache.commons.mail.HtmlEmail;
//
//public class EmailDemo {
//
//    public void sendEmail(String firstName, String lastName, String description) {
//        HtmlEmail email = new HtmlEmail();
//        try {
//            email.setHostName(config.smtpHost());
//            email.setSmtpPort(config.smtpPort());
//            email.setAuthentication(config.smtpUsername(), config.smtpPassword());
//            email.setStartTLSEnabled(config.useTLS());
//            email.setSubject("AEM Email Form Submission");
//            email.setFrom(config.senderEmail(), config.senderName());
//            email.addTo("pravinlakudzode814@gmail.com");
////            email.addTo(".com");
////            email.addTo("user3@example.com");
//
//            MessageGateway<HtmlEmail> gateway = messageGatewayService.getGateway(HtmlEmail.class);
//            if (gateway != null) {
//                gateway.send(email);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//}
