package com.ifc.library.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifc.library.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {
  
    Optional<Author> findByName(String name);

    Optional<Author> deleteByName(String name);
}
