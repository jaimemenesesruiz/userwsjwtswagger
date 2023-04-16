package com.nisum.userws.repositories;

import com.nisum.userws.entities.ERole;
import com.nisum.userws.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
