package com.cihan.emar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO {

    private long id;

    private String username;

    private String password;

    private Set<RoleDTO> roles;
}
