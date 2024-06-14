package com.ifc.library.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import com.ifc.library.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> removeUser(@PathVariable String email) {
        if(userRepository.findByEmail(email)) {
            return ResponseEntity.noContent().build();
        }
        userRepository.deleteByEmail(email);
        return ResponseEntity.notFound().build();
    }
}