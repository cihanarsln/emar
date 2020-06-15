package com.cihan.emar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {

    private long id;

    private String name;

    private String mail;

    private String password;

    private String city;

    private int memberCount;

    private Set<RoleDTO> roles;



}
