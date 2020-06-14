package com.cihan.emar.service.base;

import com.cihan.emar.dto.RoleDTO;
import org.springframework.stereotype.Component;

@Component
public interface RoleService {

    void save(RoleDTO roleDTO);

}
