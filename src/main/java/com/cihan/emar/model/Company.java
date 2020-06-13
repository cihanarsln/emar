package com.cihan.emar.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String mail;

    private String password;

    private String city;

    private int memberCount;

    @ManyToMany
    private Set<Role> roles;


}
