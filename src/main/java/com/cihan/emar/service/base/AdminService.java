package com.cihan.emar.service.base;

import com.cihan.emar.dto.AdminDTO;
import org.springframework.stereotype.Component;

@Component
public interface AdminService {

    void save(AdminDTO adminDTO);

}
