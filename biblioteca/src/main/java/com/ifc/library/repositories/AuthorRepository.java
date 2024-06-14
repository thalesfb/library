package com.ifc.library.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ifc.library.entity.Author;

import jakarta.transaction.Transactional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {
  
    Optional<Author> findByName(String name);

    @Transactional
    @Modifying
    @Query("DELETE FROM Author a WHERE a.name = :name")
    int deleteAuthorByName(String name);
}