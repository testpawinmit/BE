package com.mit.pawin.service.impl;

import com.mit.pawin.controller.AuthenticationController;
import com.mit.pawin.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${simple.java.mail.cc}")
    private String ccMailAddress;

    private static final Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);

    /**
     * 1
     *
     * @param toEmailAddress
     * @param message
     * @return
     */
    public boolean sendEmail(String toEmailAddress, String message, String subject) {

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ toEmailAddress={}, message}", toEmailAddress, message);

        try {
            //SimpleMailMessage msg = new SimpleMailMessage();
            MimeMessage msg = javaMailSender.createMimeMessage();
            //msg.setTo("buddhikachulabandara@gmail.com");
            msg.setFrom("testpawinmit@gmail.com");
            //msg.setTo(toEmailAddress);

            msg.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(
                    toEmailAddress));

            /*msg.setCc("buddhikachulabandara@gmail.com");
            msg.setCc("indikasw@cba.lk");
            msg.setCc("haritha.g@cba.lk");*/

            String[] ccArr = ccMailAddress.split(",");
            //msg.setCc(ccArr);

            for(String cc : ccArr) {
                msg.addRecipient(MimeMessage.RecipientType.CC, new InternetAddress(cc));
            }

            msg.setSubject(subject);
            //msg.setText("Hello World \n Spring Boot Email");
            msg.setText(message);
            //msg.setContent(message, "text/html; charset=utf-8");

            javaMailSender.send(msg);

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @Bean
    public JavaMailSender getJavaMailSender() {

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()");

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("testpawinmit@gmail.com");
        mailSender.setPassword("Nethmini-1991");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

}
