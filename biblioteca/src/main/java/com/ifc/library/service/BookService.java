package com.ifc.library.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifc.library.entity.Book;
import com.ifc.library.repositories.BookRepository;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public void register(Book book) {
        // Implementação do método de registro
        bookRepository.save(book);
    }

    public Book findBookByTitle(String title) {
        // Implementação do método de busca
        Optional<Book> bookOpt = bookRepository.findByTitle(title);
        return bookOpt.orElse(null);
    }

    public Book findBookByIsbn(String isbn) {
        // Implementação do método de busca
        Optional<Book> bookOpt = bookRepository.findByIsbn(isbn);
        return bookOpt.orElse(null);
    }
}