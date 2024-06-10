package com.ifc.library.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "administrator")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Administrator extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    // @OneToOne
    // @JoinColumn(name = "user_id")
    // private User user;
}
