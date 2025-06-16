package edu.xanderson.ritimoTask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private static String email;
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public boolean sendTextMail(String recipientEmail, String subject, String message) throws Exception{
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(sender);
            simpleMailMessage.setTo(recipientEmail);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(message);

            javaMailSender.send(simpleMailMessage);
            
            return true;
        } catch (Exception e) {
            throw new Exception("Erro ao enviar o email \n", e);
        }
    }


    public static String validateEmail(String email){
        return email;
    }
    public static String getEmail(){
        return email;
    }
}
