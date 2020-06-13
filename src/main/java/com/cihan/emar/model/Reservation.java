package com.cihan.emar.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Room room;

    @ManyToOne
    private Company company;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date startDate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date endDate;

    private Boolean hasExtra;

    private float cost;

}
