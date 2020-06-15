package com.cihan.emar.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private int capacity;

    private String city;

    private String state;

    private float price;

}
