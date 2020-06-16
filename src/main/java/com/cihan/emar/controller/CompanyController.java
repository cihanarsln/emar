package com.cihan.emar.controller;

import com.cihan.emar.dto.CompanyDTO;
import com.cihan.emar.service.base.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/emar/company")
@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<Object> save(@RequestBody CompanyDTO companyDTO){
        companyService.save(companyDTO);
        return new ResponseEntity<>("Company is created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<Object> findAll(){
        return new ResponseEntity<>(companyService.findAll(), HttpStatus.CREATED);
    }

}
