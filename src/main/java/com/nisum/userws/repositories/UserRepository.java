package com.nisum.userws.repositories;

import com.nisum.userws.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);

    Boolean existsByEmail(String email);

    User findByEmail(String email);
}
