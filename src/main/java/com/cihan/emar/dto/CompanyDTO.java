package com.cihan.emar.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CompanyDTO {

    private long id;

    private String name;

    private String mail;

    private String password;

    private String city;

    private int memberCount;

    private Set<RoleDTO> roles;



}
