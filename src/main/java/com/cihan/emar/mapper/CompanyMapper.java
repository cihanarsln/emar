package com.cihan.emar.mapper;

import com.cihan.emar.dto.CompanyDTO;
import com.cihan.emar.model.Company;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    @Named("toCompany")
    Company toCompany(CompanyDTO companyDTO);

    @Named("toCompanyDTO")
    CompanyDTO toCompanyDTO(Company company);

    @IterableMapping(qualifiedByName = "toCompany")
    List<Company> toCompanyList(List<CompanyDTO> companyDTOList);

    @IterableMapping(qualifiedByName = "toCompanyDTO")
    List<CompanyDTO> toCompanyDTOList(List<Company> companyList);

}
