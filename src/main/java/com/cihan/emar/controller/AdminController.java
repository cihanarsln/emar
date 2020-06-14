package com.cihan.emar.controller;

import com.cihan.emar.dto.AdminDTO;
import com.cihan.emar.service.base.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/emar/admin")
@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<Object> save(@RequestBody AdminDTO adminDTO){
        adminService.save(adminDTO);
        return new ResponseEntity<>("Admin is created successfully", HttpStatus.CREATED);
    }

}
