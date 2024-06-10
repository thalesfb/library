package com.ifc.library.dto;

import java.util.List;

public record LoansDTO(
  String id,
  String book,
  String user,
  String loanDate,
  String returnDate
  ){}