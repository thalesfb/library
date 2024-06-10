package com.ifc.library.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ifc.library.entity.*;
import com.ifc.library.repositories.*;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
public class LibraryService {

  private final Library library = Library.getInstance();

  private AuthorRepository authorRepository;
  private BookRepository bookRepository;
  private UserRepository userRepository;
  private LoanRepository loanRepository;

  public void registerAuthor(Author author) {
    authorRepository.save(author);
  }

  public Author findAuthorByName(String author) {
    Optional<Author> authorOpt = authorRepository.findByName(author);
    return authorOpt.orElse(null);
  }

  public void removeAuthor(Author author) {
    authorRepository.delete(author);
  }

  public void registerBook(Book book) {
    bookRepository.save(book);
  }

  public Book findBookByTitle(String title) {
    Optional<Book> bookOpt = bookRepository.findByTitle(title);
    return bookOpt.orElse(null);
  }

  public Book findBookByIsbn(String isbn) {
    Optional<Book> bookOpt = bookRepository.findByIsbn(isbn);
    return bookOpt.orElse(null);
  }

  public List<Book> findBooks(){
    return bookRepository.findAll();
  }

  public void removeBook(String isbn) {
    bookRepository.deleteByIsbn(isbn);
  }

  public void registerLoan(Loan loan) {
    loanRepository.save(loan);
  }

  public Loan findLoanByTitle(String title) {
    Optional<Loan> bookOpt = loanRepository.findByBookTitle(title);
    return bookOpt.orElse(null);
  }

  // public Loan findLoanByIsbn(String isbn) {
  //   Optional<Loan> bookOpt = loanRepository.findByIsbn(isbn);
  //   return bookOpt.orElse(null);
  // }

  public Loan findLoanByIsbn(String isbn){
      // armazena numa lista para retornar pois retornar um por vez
      Optional<Loan> loanOpt = loanRepository.findByBookIsbn(isbn);
      return loanOpt.orElse(null);
  }

  // public List<Loan> findLoanByEmail(String email) {
  //   return loanRepository.findByUserEmail(email);
  // }

  public List<Loan> findLoans(){
    return loanRepository.findAll();
  }

  public List<Loan> findLoansByUserEmail(String email){
    return loanRepository.findByPersonEmail(email);
  }
  
  public void removeLoan(Loan loan) {
    loanRepository.delete(loan);
  }

  public List<User> getAllUsers(){
      return userRepository.findAll();
  }
  
}
