package com.nisum.userws.services.impl;

import com.nisum.userws.entities.ERole;
import com.nisum.userws.entities.Role;
import com.nisum.userws.repositories.RoleRepository;
import com.nisum.userws.repositories.UserRepository;
import com.nisum.userws.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(ERole name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }
}
