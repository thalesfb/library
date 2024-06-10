package com.ifc.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifc.library.entity.Student;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    Optional<Student> findByName(String name);

    Optional<Student> findByEmail(String email);

    // Optional<Student> findByCourse(String course);

    Optional<Student> findByRegistration(String registration);
}
