package com.ifc.library.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import com.ifc.library.observer.UserObserver;

@Component
public class UserObserverFactory {

    @Autowired
    private JavaMailSender mailSender;

    public UserObserver createUserObserver(String email) {
        return new UserObserver(email, mailSender);
    }
}