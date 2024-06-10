package com.ifc.library.service;

import com.ifc.library.observer.LoanSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class LoanService {
    private final LoanSubject loanSubject;
    private final JavaMailSender mailSender;

    @Autowired
    public LoanService(LoanSubject loanSubject, JavaMailSender mailSender) {
        this.loanSubject = loanSubject;
        this.mailSender = mailSender;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void checkLoanDates() {
        // Verifique as datas de empréstimo aqui e notifique os observadores
        loanSubject.notifyObservers();
    }

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}