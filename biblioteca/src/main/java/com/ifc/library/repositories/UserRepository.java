package com.ifc.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.ifc.library.entity.User;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByCpf(String cpf);

    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findByEmail(String email);
    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.email = :email")
    Optional<User> deleteByEmail(String email);

    Optional<User> findByName(String name);
    
    List<User> findAll();
}