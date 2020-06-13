package com.cihan.emar.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class AdminDTO {

    private long id;

    private String username;

    private String password;

    private Set<RoleDTO> roles;

    private Set<RoomDTO> rooms;
}
