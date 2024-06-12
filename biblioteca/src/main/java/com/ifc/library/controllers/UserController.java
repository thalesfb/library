package com.ifc.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import com.ifc.library.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import com.ifc.library.entity.User;
import com.ifc.library.service.UserService;
import com.ifc.library.dto.UserDTO;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    public final UserService userService;
    public final UserRepository userRepository;

    @DeleteMapping("/{email}")
    public ResponseEntity<String> removeUser(@PathVariable String email) {
        Optional<User> userOpt = this.userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            userService.removeUser(email);
            return ResponseEntity.ok("User removed");
        } else {
            return ResponseEntity.badRequest().body("User not found");
        }
    }
}
