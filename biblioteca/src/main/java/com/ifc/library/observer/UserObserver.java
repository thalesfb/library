package com.ifc.library.observer;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@AllArgsConstructor
public class UserObserver implements Observer {
    private final String email;
    private final JavaMailSender mailSender;

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
