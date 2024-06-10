package com.ifc.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifc.library.entity.User;
import com.ifc.library.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User register(User user) {
        // Implementação do método de registro
        return userRepository.save(user);
    }

    public User login(String email, String password) {
        // Implementação do método de login
        Optional<User> userOpt = userRepository.findByEmailAndPassword(email, password);
        return userOpt.orElse(null);
    }

    public User findUserByEmail(String email){
        Optional<User> userOpt = userRepository.findByEmail(email);
        return userOpt.orElse(null);
    }
    public void removeUser(String email) {
        // Implementação do método de remoção
        userRepository.deleteByEmail(email);
    }
}
