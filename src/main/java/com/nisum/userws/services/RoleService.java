package com.nisum.userws.services;

import com.nisum.userws.entities.ERole;
import com.nisum.userws.entities.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(ERole name);

    Role save(Role role);
}
