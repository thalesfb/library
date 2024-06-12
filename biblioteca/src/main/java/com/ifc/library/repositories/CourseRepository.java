package com.ifc.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ifc.library.entity.Course;
import java.util.Optional;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {

    Optional<Course> findByName(String name);
    Optional<Course> findById(String id);
    Optional<Course> deleteByName(String name);
    List<Course> findAll();
}
