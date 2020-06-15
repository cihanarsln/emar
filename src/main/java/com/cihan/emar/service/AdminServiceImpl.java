package com.cihan.emar.service;

import com.cihan.emar.dto.AdminDTO;
import com.cihan.emar.error.UsernameAlreadyUsedException;
import com.cihan.emar.mapper.AdminMapper;
import com.cihan.emar.repository.AdminRepository;
import com.cihan.emar.service.base.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public void save(AdminDTO adminDTO) {
        checkUsername(adminDTO.getUsername());
        adminMapper.toAdminDTO(adminRepository.save(adminMapper.toAdmin(adminDTO)));
    }

    private void checkUsername(String username){
        if (adminRepository.findByUsername(username) != null) throw new UsernameAlreadyUsedException();
    }
}
