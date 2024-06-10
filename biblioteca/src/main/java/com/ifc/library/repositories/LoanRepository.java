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
  
    Optional<Loan> findByBookTitle(String title);
    Optional<Loan> findByBookIsbn(String isbn);
    Optional<Loan> findByPersonEmail(String email);
    // @Query("SELECT l FROM Loan l WHERE l.user.email = :email")
    // List<Loan> findByUserEmail(@Param("email") String email);
    List<Loan> findAll();
}