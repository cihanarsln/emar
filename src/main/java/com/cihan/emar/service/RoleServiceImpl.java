package com.cihan.emar.service;

import com.cihan.emar.dto.RoleDTO;
import com.cihan.emar.error.UsernameAlreadyUsedException;
import com.cihan.emar.mapper.RoleMapper;
import com.cihan.emar.repository.RoleRepository;
import com.cihan.emar.service.base.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void save(RoleDTO roleDTO) {
        checkUsername(roleDTO.getName());
        roleMapper.toRoleDTO(roleRepository.save(roleMapper.toRole(roleDTO)));
    }

    private void checkUsername(String name){
        if (roleRepository.findByName(name) != null) throw new UsernameAlreadyUsedException();
    }

}
