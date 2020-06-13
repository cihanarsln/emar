package com.cihan.emar.service;

import com.cihan.emar.dto.AdminDTO;
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
    public AdminDTO save(AdminDTO adminDTO) {
        return adminMapper.toAdminDTO(adminRepository.save(adminMapper.toAdmin(adminDTO)));
    }

}
