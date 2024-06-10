package com.ifc.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifc.library.entity.Loan;

import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, String> {
  
    Optional<Loan> findByTitle(String title);
    Optional<Loan> findByIsbn(String isbn);
    Optional<Loan> findByEmail(String email);
    List<Loan> findLoanByEmail(String email);
}