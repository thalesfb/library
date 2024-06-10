package com.ifc.library.factory;

import com.ifc.library.entity.Student;
import com.ifc.library.entity.User;
import org.springframework.stereotype.Component;

@Component
public class StudentFactory implements UserFactory{
  @Override
  public User createUser() {
    return new Student();
  }

  public Student createStudent(String registration) {
    Student student = new Student();
    student.setRegistration(registration);
    return student;
  }
}
