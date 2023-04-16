package com.nisum.userws.services;

import com.nisum.userws.entities.Phone;
import com.nisum.userws.entities.User;
import com.nisum.userws.repositories.PhoneRepository;
import com.nisum.userws.repositories.UserRepository;
import com.nisum.userws.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Mock
    private UserRepository userRespository;

    @Mock
    private PhoneRepository phoneRepository;

    @InjectMocks
    private UserService userService= new UserServiceImpl();

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        List<Phone> phoneList = new ArrayList<Phone>(Arrays.asList(new Phone().builder()
                .citycode("1")
                .countrycode("2")
                .number("12345")
                .build()));
        user = new User()
                .builder()
                .username("jose@gmail.com")
                .phones(phoneList)
                .email("jose@gmail.com")
                .name("Jose")
                .build();

    }

    @Test
    void existsByEmailTrue() {
        Mockito.when(userRespository.existsByEmail(Mockito.anyString())).thenReturn(true);
        //given
        User newUser = user;

        //when
        Boolean exist=userService.existsByEmail(newUser.getEmail());
        //then
        assertTrue(exist);
    }

    @Test
    void existsByEmailFalse() {
        Mockito.when(userRespository.existsByEmail(Mockito.anyString())).thenReturn(false);
        //given
        User newUser = user;

        //when
        Boolean exist=userService.existsByEmail(newUser.getEmail());
        //then
        assertFalse(exist);
    }
    @Test
    void findByEmailNull() {
        Mockito.when(userRespository.findByEmail(Mockito.anyString())).thenReturn(null);
        //given
        User newUser = user;

        //when
        User found=userService.findByEmail(newUser.getEmail());
        //then
        assertNull(found);
    }

    @Test
    void findByEmailNotNull() {
        Mockito.when(userRespository.findByEmail(Mockito.anyString())).thenReturn(user);
        //given
        User newUser = user;

        //when
        User found=userService.findByEmail(newUser.getEmail());
        //then
        assertNotNull(found);
    }

    @Test
    void saveSave() {
        Mockito.when(userRespository.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(userRespository.findByEmail(Mockito.anyString())).thenReturn(null);
        //given
        User newUser = user;

        //when
        User saved=userService.save(newUser);
        //then
        assertNotNull(saved);
        assertEquals(user.getName(),saved.getName());
        assertEquals(user.getEmail(),saved.getEmail());
        assertEquals(user.getUsername(),saved.getUsername());
        for(int i=0;i<user.getPhones().size();i++){
            assertEquals(user.getPhones().get(i).getCitycode(),saved.getPhones().get(i).getCitycode());
            assertEquals(user.getPhones().get(i).getCountrycode(),saved.getPhones().get(i).getCountrycode());
            assertEquals(user.getPhones().get(i).getNumber(),saved.getPhones().get(i).getNumber());
        }
        Mockito.verify(phoneRepository, Mockito.atLeast(1)).save(Mockito.any(Phone.class));
    }

    @Test
    void saveUpdate() {
        Mockito.when(userRespository.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(userRespository.findByEmail(Mockito.anyString())).thenReturn(user);
        //given
        User newUser = user;

        //when
        User saved=userService.save(newUser);
        //then
        assertNotNull(saved);
        assertEquals(user.getName(),saved.getName());
        assertEquals(user.getEmail(),saved.getEmail());
        assertEquals(user.getUsername(),saved.getUsername());
        for(int i=0;i<user.getPhones().size();i++){
            assertEquals(user.getPhones().get(i).getCitycode(),saved.getPhones().get(i).getCitycode());
            assertEquals(user.getPhones().get(i).getCountrycode(),saved.getPhones().get(i).getCountrycode());
            assertEquals(user.getPhones().get(i).getNumber(),saved.getPhones().get(i).getNumber());
        }
        Mockito.verify(phoneRepository, Mockito.never()).save(Mockito.any(Phone.class));
    }
}