package com.cihan.emar.controller;

import com.cihan.emar.dto.RoleDTO;
import com.cihan.emar.service.base.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/emar/role")
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    @ResponseBody
    public RoleDTO save(@RequestBody RoleDTO roleDTO){
        return roleService.save(roleDTO);
    }

}
