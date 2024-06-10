package com.ifc.library.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifc.library.entity.Loan;
import com.ifc.library.repositories.LoanRepository;
import com.ifc.library.repositories.UserRepository;
import java.util.List;

@Service
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;

    public void register(Loan loan) {
        // Implementação do método de registro
        loanRepository.save(loan);
    }

    public Loan findLoanByTitle(String title) {
        // Implementação do método de busca
        Optional<Loan> bookOpt = loanRepository.findByTitle(title);
        return bookOpt.orElse(null);
    }

    public Loan findLoanByIsbn(String isbn) {
        // Implementação do método de busca
        Optional<Loan> bookOpt = loanRepository.findByIsbn(isbn);
        return bookOpt.orElse(null);
    }

    public Loan findLoanByEmail(String email) {
        List<Loan> loans = loanRepository.findLoanByEmail(email);
    }
}