package com.cihan.emar.service;

import com.cihan.emar.dto.CompanyDTO;
import com.cihan.emar.mapper.CompanyMapper;
import com.cihan.emar.repository.CompanyRepository;
import com.cihan.emar.service.base.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public void save(CompanyDTO companyDTO) {
        companyMapper.toCompanyDTO(companyRepository.save(companyMapper.toCompany(companyDTO)));
    }

}
