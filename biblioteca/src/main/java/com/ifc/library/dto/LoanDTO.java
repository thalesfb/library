package com.ifc.library.dto;

public record LoanDTO(
  String id,
  String book,
  String user,
  String loanDate,
  String returnDate
  ){}