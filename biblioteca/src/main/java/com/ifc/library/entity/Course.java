package com.ifc.library.entity;

import jakarta.persistence.*;
import java.util.List;


import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "course")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private int duration;
    
    @OneToMany(mappedBy = "course")
    private List<Student> students;

}
