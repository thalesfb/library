package com.ifc.library.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifc.library.dto.LoginRequestDTO;
import com.ifc.library.entity.User;
import com.ifc.library.repositories.UserRepository;
import com.ifc.library.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @DeleteMapping("/")
    public ResponseEntity removeUser(@RequestBody LoginRequestDTO body) {
        Optional<User> userOpt = this.userRepository.findByEmail(body.email());
        if(userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        } else if(passwordEncoder.matches(body.password(), userOpt.get().getPassword())) {
                userService.removeUser(body.email());
                return ResponseEntity.ok("User removed");
        } else {
                return ResponseEntity.badRequest().body("Invalid password");
        }
    }
}