package com.ifc.library.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import com.ifc.library.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> removeUser(@PathVariable String email) {
        if(userService.findByEmail(email) == null){
            return ResponseEntity.notFound().build();
            }
        userService.removeUser(email);
        return ResponseEntity.noContent().build();
    }
}