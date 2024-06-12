package com.ifc.library.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ifc.library.dto.LoginRequestDTO;
import com.ifc.library.dto.RegisterRequestDTO;
import com.ifc.library.dto.ResponseDTO;
import com.ifc.library.entity.User;
import com.ifc.library.factory.StudentFactory;
import com.ifc.library.factory.TeacherFactory;
import com.ifc.library.repositories.UserRepository;
import com.ifc.library.infra.security.TokenService;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.text.ParseException;
import java.util.Date;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final StudentFactory studentFactory;
    private final TeacherFactory teacherFactory;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body) {
        User user = this.userRepository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body) {
        Optional<User> user = this.userRepository.findByEmail(body.email());

        if (user.isEmpty()) {
            User newUser;
            
            if (body.type().equalsIgnoreCase("student")) {
                newUser = studentFactory.createStudent(body.registration());
            } else if (body.type().equalsIgnoreCase("teacher")) {
                newUser = teacherFactory.createTeacher(body.department());
            } else {
                return ResponseEntity.badRequest().build();
            }

            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setEmail(body.email());
            newUser.setName(body.name());
            newUser.setCpf(body.cpf());

            try {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date birthDate = formatter.parse(body.birthDate());
                newUser.setBirthDate(birthDate);
            } catch (ParseException e) {
                return ResponseEntity.badRequest().body("Invalid date format. Use 'yyyy-MM-dd'.");
            }

            this.userRepository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}