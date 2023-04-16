package com.nisum.userws.services;

import com.nisum.userws.entities.User;

public interface UserService {
    public Boolean existsByEmail(String email);

    public User findByEmail(String email);

    public User save(User userRequestDto);

}
