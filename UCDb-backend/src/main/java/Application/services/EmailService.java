package Application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void send(String emaill) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(emaill);
        email.setSubject("Bem vindo ao UCDb :) !!");
        email.setText("Seu cadastro foi realizado com sucesso.");
        this.mailSender.send(email);
    }
}
