package com.cihan.emar.service.base;

import com.cihan.emar.dto.CompanyDTO;
import org.springframework.stereotype.Component;

@Component
public interface CompanyService {

    void save(CompanyDTO companyDTO);

}
