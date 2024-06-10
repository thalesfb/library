package com.ifc.library.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String registration;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    // @OneToOne
    // @JoinColumn(name = "user_id")
    // private User user;    
}
