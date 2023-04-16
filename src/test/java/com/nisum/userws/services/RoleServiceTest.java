package com.nisum.userws.services;

import com.nisum.userws.entities.ERole;
import com.nisum.userws.entities.Role;
import com.nisum.userws.entities.User;
import com.nisum.userws.repositories.RoleRepository;
import com.nisum.userws.services.impl.RoleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RoleServiceTest {

    @Mock
    private RoleRepository roleRespository;


    @InjectMocks
    private RoleService roleService= new RoleServiceImpl();

    private Role role;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        role=new Role().builder()
        .name(ERole.ROLE_USER)
        .build();
    }

    @Test
    void findByNameNull() {
        Mockito.when(roleService.findByName(Mockito.any(ERole.class))).thenReturn(null);
        //given
        Role newRole = role;

        //when
        Optional<Role> found=roleService.findByName(newRole.getName());
        //then
        assertNull(found);
    }

    @Test
    void findByNameNotNull() {
        Mockito.when(roleService.findByName(Mockito.any(ERole.class))).thenReturn(Optional.of(role));
        //given
        Role newRole = role;

        //when
        Optional<Role> found=roleService.findByName(newRole.getName());
        //then
        assertNotNull(found);
    }

    @Test
    void save() {
        Mockito.when(roleService.save(Mockito.any(Role.class))).thenReturn(role);
        //given
        Role newRole = role;

        //when
        Role found=roleService.save(newRole);
        //then
        assertNotNull(found);
        assertEquals(role.getName(),found.getName());
    }
}