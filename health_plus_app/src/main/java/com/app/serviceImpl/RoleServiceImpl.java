package com.app.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entities.Role;
import com.app.repository.RoleRepository;
import com.app.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
    private static final String DEFAULT_ROLE = "ROLE_USER";

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getDefaultRole() {
        return this.roleRepository.findOneByAuthority(DEFAULT_ROLE);
    }

    @Override
    public Role getRoleByAuthority(String authority) {
        return this.roleRepository.findOneByAuthority(authority);
    }
}
