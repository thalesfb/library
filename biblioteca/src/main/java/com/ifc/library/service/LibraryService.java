package com.ifc.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifc.library.entity.Author;
import com.ifc.library.entity.Book;
import com.ifc.library.entity.Course;
import com.ifc.library.entity.Library;
import com.ifc.library.entity.Loan;
import com.ifc.library.entity.User;
import com.ifc.library.factory.UserObserverFactory;
import com.ifc.library.observer.Subject;
import com.ifc.library.observer.UserObserver;
import com.ifc.library.repositories.AuthorRepository;
import com.ifc.library.repositories.BookRepository;
import com.ifc.library.repositories.CourseRepository;
import com.ifc.library.repositories.LoanRepository;
import com.ifc.library.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LibraryService {

  private final Library library = Library.getInstance();

  @Autowired
  private AuthorRepository authorRepository;
  @Autowired
  private BookRepository bookRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private LoanRepository loanRepository;
  @Autowired
  private UserObserverFactory userObserverFactory;
  @Autowired
  private CourseRepository courseRepository;

  public void registerAuthor(Author author) {
    authorRepository.save(author);
  }

  public Author findAuthorByName(String author) {
    Optional<Author> authorOpt = authorRepository.findByName(author);
    return authorOpt.orElse(null);
  }

  public List<Author> findAuthors(){
    return authorRepository.findAll();
  }
  
  public void removeAuthor(String name) {
    authorRepository.deleteByName(name);
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
  
  public void removeLoan(String id) {
    loanRepository.deleteById(id);
  }

  public List<User> getAllUsers(){
      return userRepository.findAll();
  }

  public void adicionarObservador(Subject subject, String email) {
      UserObserver observer = userObserverFactory.createUserObserver(email);
      subject.addObserver(observer);
  }

  public void registerCourse(Course course) {
    courseRepository.save(course);
  }

  public List<Course> findCourses() {
    return courseRepository.findAll();
  }

  public void removeCourse(String id) {
    courseRepository.deleteById(id);
  }
  
}
