package edu.xanderson.ritimoTask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public boolean sendTextMail(String recipientEmail, String subject, String message) throws Exception{
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper email = new MimeMessageHelper(mimeMessage,
                                                true,
                                                "utf-8");

            email.setFrom(sender);
            email.setTo(recipientEmail);
            email.setSubject(subject);
            email.setText(message, true);

            
            javaMailSender.send(mimeMessage);
            
            return true;
        } catch (Exception e) {
            throw new Exception("Erro ao enviar o email \n", e);
        }
    }
}
