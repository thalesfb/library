package com.ifc.library.observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class UserObserver implements Observer {
    private String email;

    @Autowired
    private JavaMailSender mailSender;

    public UserObserver(String email) {
        this.email = email;
    }

    @Override
    public void update(String mensagem) {
        sendEmail(mensagem);
    }

    private void sendEmail(String mensagem) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Notificação de Empréstimo");
        message.setText(mensagem);
        mailSender.send(message);
    }
}
