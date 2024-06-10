package com.ifc.library.dto;

public record RegisterRequestDTO (
    String name,
    String email,
    String password,
    String cpf,
    String birthDate,
    String type,
    String registration,
    String department
){}