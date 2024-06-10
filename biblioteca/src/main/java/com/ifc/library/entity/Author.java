package com.ifc.library.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "author")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private Date birthDate;
    private String biography;

    @OneToMany(mappedBy = "author")
    private List<Book> books;

}
