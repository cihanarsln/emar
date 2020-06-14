package com.cihan.emar.controller;

import com.cihan.emar.dto.AdminDTO;
import com.cihan.emar.service.base.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/emar/admin")
@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping
    @ResponseBody
    public AdminDTO save(@RequestBody AdminDTO adminDTO){
        return adminService.save(adminDTO);
    }

}
