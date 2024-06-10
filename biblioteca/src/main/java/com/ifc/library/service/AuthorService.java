package com.ifc.library.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifc.library.entity.Author;
import com.ifc.library.repositories.AuthorRepository;


@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public void register(Author author) {
        // Implementação do método de registro
        authorRepository.save(author);
    }

    public Author findAuthorByName(String author) {
        // Implementação do método de busca
        Optional<Author> bookOpt = authorRepository.findByName(author);
        return bookOpt.orElse(null);
    }

    
}