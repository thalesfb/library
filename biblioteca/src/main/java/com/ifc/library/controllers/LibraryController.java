package com.ifc.library.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifc.library.dto.AuthorDTO;
import com.ifc.library.dto.BookDTO;
import com.ifc.library.dto.CourseDTO;
import com.ifc.library.dto.LoanDTO;
import com.ifc.library.entity.Author;
import com.ifc.library.entity.Book;
import com.ifc.library.entity.Course;
import com.ifc.library.entity.Loan;
import com.ifc.library.entity.User;
import com.ifc.library.repositories.AuthorRepository;
import com.ifc.library.repositories.BookRepository;
import com.ifc.library.repositories.CourseRepository;
import com.ifc.library.repositories.LoanRepository;
import com.ifc.library.repositories.UserRepository;
import com.ifc.library.service.LibraryService;

import lombok.RequiredArgsConstructor;

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
  public ResponseEntity<String> removeBook(@RequestBody BookDTO body) {
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
  
  

  @DeleteMapping("/author")
  public ResponseEntity removeAuthor(@RequestBody AuthorDTO body) {
    Optional<Author> authorOpt = this.authorRepository.findByName(body.name());
    if(authorOpt.isEmpty()) {
        return ResponseEntity.badRequest().body("Author not found");
    } else{
            libraryService.removeAuthor(body.name());
            return ResponseEntity.ok("Author removed");
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





  @DeleteMapping("/loan")
  public ResponseEntity removeLoan(@RequestBody LoanDTO body) {
    Optional<Loan> loanOpt = this.loanRepository.findById(body.id());
    if (loanOpt.isEmpty()) {
      return ResponseEntity.badRequest().body("Loan not found");
    } else {
      libraryService.removeLoan(body.id());
      return ResponseEntity.ok("Loan Removed");
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

  @DeleteMapping("/course")
  public ResponseEntity removeCourse(@RequestBody CourseDTO body) {
    Optional<Course> courseOpt = this.courseRepository.findById(body.id());
    if (courseOpt.isEmpty()) {
      return ResponseEntity.badRequest().body("Course not found");
    } else {
      libraryService.removeCourse(body.id());
      return ResponseEntity.ok("Course removed");
      
    }
  }
}
