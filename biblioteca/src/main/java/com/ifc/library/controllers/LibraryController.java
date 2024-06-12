package com.ifc.library.controllers;

import com.ifc.library.entity.*;
import com.ifc.library.service.*;
import lombok.RequiredArgsConstructor;
import com.ifc.library.dto.*;
import com.ifc.library.repositories.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/library")
@RequiredArgsConstructor
public class LibraryController {
 
  private final LibraryService libraryService;
  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;
  private final UserRepository userRepository;
  private final LoanRepository loanRepository;
  private final CourseRepository courseRepository;

  @PostMapping("/book")
  public ResponseEntity registerBook(@RequestBody BookDTO body) {
    Optional<Book> bookOpt = this.bookRepository.findByIsbn(body.isbn());

    if (bookOpt.isEmpty()) {
      Book book = new Book();
      book.setIsbn(body.isbn());
      book.setTitle(body.title());
      // book.setAuthor(authorRepository.findByName(body.author()));

      libraryService.registerBook(book);
      return ResponseEntity.ok("Book registered");
    } else {
      return ResponseEntity.badRequest().body("Book already registered");
    }
  }

  @DeleteMapping("/book")
  public ResponseEntity removeBook(@RequestBody BookDTO body) {
    Optional<Book> bookOpt = this.bookRepository.findByIsbn(body.isbn());
    if (bookOpt.isEmpty()) {
      return ResponseEntity.badRequest().body("Book not found");
    } else {
      libraryService.removeBook(body.isbn());
      return ResponseEntity.ok("Book removed");
    }
  }

  @GetMapping("/book")
  public ResponseEntity<List<BookDTO>> listBooks() {
    List<Book> books = libraryService.findBooks();
    List<BookDTO> booksDTO = books.stream().map(book -> {
    BookDTO bookDTO = new BookDTO(book.getIsbn(), book.getTitle(), book.getAuthor().getName());
    return bookDTO;
    }).collect(Collectors.toList());
    
    return ResponseEntity.ok(booksDTO);
  }

  @PostMapping("/author")
  public ResponseEntity<String> registerAuthor(@RequestBody AuthorDTO body) {
    Optional<Author> authorOpt = this.authorRepository.findByName(body.name());

    if (!authorOpt.isPresent()) {
      Author author = new Author();
      author.setName(body.name());

      libraryService.registerAuthor(author);
      return ResponseEntity.ok("Author registered");
    } else {
      return ResponseEntity.badRequest().body("Author already registered");
    }
  }

  @DeleteMapping("/author/{name}")
  public ResponseEntity<String> removeAuthor(@PathVariable String name) {
    Optional<Author> authorOpt = this.authorRepository.findByName(name);

    if (authorOpt.isPresent()) {
      libraryService.removeAuthor(authorOpt.get());
      return ResponseEntity.ok("Author removed");
    } else {
      return ResponseEntity.badRequest().body("Author not found");
    }
  }

  @GetMapping("/author")
  public ResponseEntity<List<AuthorDTO>> listAuthors() {
    List<Author> authors = libraryService.findAuthors();
    List<AuthorDTO> authorsDTO = authors.stream().map(author -> {
    AuthorDTO authorDTO = new AuthorDTO(
      author.getName(),
      author.getBirthDate().toString(),
      author.getBiography()
    );
    return authorDTO;
    }).collect(Collectors.toList());
    
    return ResponseEntity.ok(authorsDTO);
  }

  @GetMapping("/loan")
  public ResponseEntity<List<LoanDTO>> listLoans() {
    List<Loan> loans = libraryService.findLoans();
    List<LoanDTO> loansDTO = loans.stream().map(loan -> {
    LoanDTO loanDTO = new LoanDTO(
      loan.getId(),
      loan.getBook().getIsbn(),
      loan.getUser().getName(),
      loan.getLoanDate().toString(),
      loan.getReturnDate().toString()
    );
    return loanDTO;
    }).collect(Collectors.toList());
    
    return ResponseEntity.ok(loansDTO);
  }

  @PostMapping("/loan")
  public ResponseEntity<String> registerLoan(@RequestBody LoanDTO body) {
    Optional<Book> bookOpt = this.bookRepository.findByIsbn(body.book());
    Optional<User> userOpt = this.userRepository.findByName(body.user());

    if (bookOpt.isPresent() && userOpt.isPresent()) {
      Loan loan = new Loan();
      loan.setBook(bookOpt.get());
      loan.setUser(userOpt.get());
      try {
          SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
          Date loanDate = formatter.parse(body.loanDate());
          loan.setLoanDate(loanDate);
      } catch (ParseException e) {
          return ResponseEntity.badRequest().body("Invalid date format. Use 'yyyy-MM-dd'.");
      }
      try {
          SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
          Date returnDate = formatter.parse(body.returnDate());
          loan.setReturnDate(returnDate);
      } catch (ParseException e) {
          return ResponseEntity.badRequest().body("Invalid date format. Use 'yyyy-MM-dd'.");
      }
      libraryService.registerLoan(loan);
      return ResponseEntity.ok("Loan registered");
    } else {
      return ResponseEntity.badRequest().body("Book or user not found");
    }
  }

  @DeleteMapping("/loan/{id}")
  public ResponseEntity<String> removeLoan(@PathVariable String id) {
    Optional<Loan> loanOpt = this.loanRepository.findById(id);
    if (loanOpt.isPresent()) {
      libraryService.removeLoan(loanOpt.get());
      return ResponseEntity.ok("Loan removed");
    } else {
      return ResponseEntity.badRequest().body("Loan not found");
    }
  }

  @GetMapping("/course")
  public ResponseEntity<List<CourseDTO>> listCourses() {
    List<Course> courses = libraryService.findCourses();
    List<CourseDTO> coursesDTO = courses.stream().map(course -> {
    CourseDTO courseDTO = new CourseDTO(course.getId(), course.getName());
    return courseDTO;
    }).collect(Collectors.toList());
    
    return ResponseEntity.ok(coursesDTO);
  }

  @PostMapping("/course")
  public ResponseEntity<String> registerCourse(@RequestBody CourseDTO body) {
    Optional<Course> courseOpt = this.courseRepository.findByName(body.name());

    if (!courseOpt.isPresent()) {
      Course course = new Course();
      course.setName(body.name());

      libraryService.registerCourse(courseOpt.get());
      return ResponseEntity.ok("Course registered");
    } else {
      return ResponseEntity.badRequest().body("Course already registered");
    }
  }

  @DeleteMapping("/course/{id}")
  public ResponseEntity<String> removeCourse(@PathVariable String id) {
    Optional<Course> courseOpt = this.courseRepository.findById(id);
    if (courseOpt.isPresent()) {
      libraryService.removeCourse(courseOpt.get());
      return ResponseEntity.ok("Course removed");
    } else {
      return ResponseEntity.badRequest().body("Course not found");
    }
  }
}
