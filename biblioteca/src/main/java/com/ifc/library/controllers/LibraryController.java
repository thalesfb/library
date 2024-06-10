package com.ifc.library.controllers;

import com.ifc.library.entity.*;
import com.ifc.library.service.LibraryService;

import lombok.RequiredArgsConstructor;

import com.ifc.library.dto.BookDTO;
import com.ifc.library.repositories.BookRepository;
import com.ifc.library.repositories.UserRepository;
import com.ifc.library.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/library")
@RequiredArgsConstructor
public class LibraryController {
 
  private final LibraryService libraryService;
  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;

  @PostMapping("/book")
  public ResponseEntity<String> registerBook(@RequestBody BookDTO body) {
    Optional<Book> bookOpt = this.bookRepository.findByIsbn(body.isbn());

    if (!bookOpt.isPresent()) {
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

  @DeleteMapping("/book/{isbn}")
  public ResponseEntity<String> removeBook(@PathVariable String isbn) {
    Optional<Book> bookOpt = this.bookRepository.findByIsbn(isbn);
    if (bookOpt.isPresent()) {
      libraryService.removeBook(isbn);
      return ResponseEntity.ok("Book removed");
    } else {
      return ResponseEntity.badRequest().body("Book not found");
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
}
