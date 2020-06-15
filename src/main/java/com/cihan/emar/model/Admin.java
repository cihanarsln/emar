package com.cihan.emar.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    private String password;

    @ManyToMany
    private Set<Role> roles;

}
