package com.ifc.library.controllers;

import com.ifc.library.entity.*;
import com.ifc.library.service.LibraryService;

import lombok.RequiredArgsConstructor;

import com.ifc.library.dto.BookDTO;
import com.ifc.library.dto.UserDTO;
import com.ifc.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/library")
@RequiredArgsConstructor
public class LibraryController {
 
  private final LibraryService libraryService;
  private final BookRepository bookRepository;
  

  @PostMapping("/book")
  public ResponseEntity<String> registerBook(@RequestBody BookDTO body) {
    Optional<Book> bookOpt = this.bookRepository.findByIsbn(body.isbn());

    if (bookOpt == null) {
      Book book = new Book;
      book.setIsbn(body.isbn());
      book.setTitle(body.title());
      book.setAuthor(body.author());

      libraryService.registerBook(book);
      return ResponseEntity.ok("Book registered");
    } else {
      return ResponseEntity.badRequest().body("Book already registered");
    }
  }

  @DeleteMapping("/book/{isbn}")
  public ResponseEntity<String> removeBook(@PathVariable String isbn) {
    Book book = this.bookRepository.findByIsbn(isbn);

    if (book != null) {
      libraryService.removeBook(isbn);
      return ResponseEntity.ok("Book removed");
    } else {
      return ResponseEntity.badRequest().body("Book not found");
    }
  }

  @GetMapping("/book")
  public ResponseEntity<List<BookDTO>> listBooks() {
    List<Book> books = libraryService.findBooks();
    List<BookDTO> booksDTO = books.stream().map(book -> 
    BookDTO bookDTO = new BookDTO();
    bookDTO.setIsbn(book.getIsbn());
    bookDTO.setTitle(book.getTitle());
    bookDTO.setAuthor(book.getAuthor());
    return bookDTO;
    ).collect(Collectors.toList());
    
    return ResponseEntity.ok(booksDTO);
  }
}
