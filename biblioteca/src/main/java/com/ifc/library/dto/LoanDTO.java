package com.ifc.library.dto;

public record LoanDTO(
  String id,
  String isbn,
  String user,
  String loanDate,
  String returnDate
  ){}