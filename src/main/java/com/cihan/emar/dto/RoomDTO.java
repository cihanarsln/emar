package com.cihan.emar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {

    private long id;

    private String name;

    private int capacity;

    private String city;

    private String district;

    private float price;

}
