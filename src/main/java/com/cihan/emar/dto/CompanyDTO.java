package com.cihan.emar.dto;

import lombok.Data;

import java.util.Set;

@Data
public class CompanyDTO {

    private long id;

    private String name;

    private String mail;

    private String password;

    private String city;

    private int memberCount;

    private Set<RoleDTO> roles;



}
