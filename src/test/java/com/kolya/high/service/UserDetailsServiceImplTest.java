package com.kolya.high.service;

import com.kolya.high.model.Role;
import com.kolya.high.model.User;
import com.kolya.high.repo.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.Set;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setPassword("Hello");
        user.setId(1L);
        user.setUsername("Name");
        user.setRoles(Set.of(Role.PATIENT));
    }

    @Test
    public void testLoadUserByUsername_UsernameExist_UserReturned() {
        when(userRepository.findByUsername(any(String.class))).thenReturn(user);

        UserDetails foundUser = userDetailsService.loadUserByUsername("test");

        assertEquals(user, foundUser);
    }

    @Test
    public void testLoadUserByUsername_UsernameNotExist_ThrowsUsernameNotFoundException() {
        when(userRepository.findByUsername(any(String.class))).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername("test");
        });
    }
}