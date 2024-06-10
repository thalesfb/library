package com.ifc.library.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Loan> loans;
}
