package com.ifc.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ifc.library.entity.Loan;
import java.util.Optional;
import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, String> {
  
    @Query("SELECT l FROM Loan l WHERE l.book.title = :title")
    Optional<Loan> findByBookTitle(@Param("title") String title);

    @Query("SELECT l FROM Loan l WHERE l.book.isbn = :isbn")
    Optional<Loan> findByBookIsbn(@Param("isbn") String isbn);

    @Query("SELECT l FROM Loan l WHERE l.user.email = :email")
    List<Loan> findByPersonEmail(@Param("email") String email);

    List<Loan> findAll();
}