package com.cihan.emar.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ReservationDTO {

    private long id;

    private RoomDTO room;

    private CompanyDTO company;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date startDate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date endDate;

    private Boolean hasExtra;

    private float cost;

}
