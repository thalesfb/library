package com.ifc.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ifc.library.entity.User;
import com.ifc.library.service.UserService;
import com.ifc.library.dto.UserDTO;

@RestController
@RequestMapping("/user")
public class UserController {

    // listando livros
    // @Autowired
    // private UserService userService;

    // @PostMapping("/book")
    // public ResponseEntity<User> 

    // listando emprestimos do usuario
}
