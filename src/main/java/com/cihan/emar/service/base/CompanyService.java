package com.cihan.emar.service.base;

import com.cihan.emar.dto.CompanyDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface CompanyService {

    void save(CompanyDTO companyDTO);

    Optional<CompanyDTO> findById(long id);

}
