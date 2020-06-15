package com.cihan.emar.dto;

import lombok.Data;

import java.util.Set;

@Data
public class AdminDTO {

    private long id;

    private String username;

    private String password;

    private Set<RoleDTO> roles;
}
