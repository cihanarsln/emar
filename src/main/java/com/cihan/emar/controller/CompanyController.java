package com.cihan.emar.controller;

import com.cihan.emar.dto.CompanyDTO;
import com.cihan.emar.service.base.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/emar/company")
@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping
    @ResponseBody
    public CompanyDTO save(@RequestBody CompanyDTO companyDTO){
        return companyService.save(companyDTO);
    }

}
