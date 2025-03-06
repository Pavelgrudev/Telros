package ru.restapi.Telros.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class SecurityConfigTest {

    @Autowired
    private SecurityFilterChain securityFilterChain;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

/**
 * Проверка на создания бина
 * */
    @Test
    void securityFilterChain_ShouldBeCreated() {
        assertNotNull(securityFilterChain);
    }

/**
 * Загружаем несуществующего юзера
 * */
    @Test
    void userDetailsService_ShouldThrowExceptionForUnknownUser() {
        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername("unknown");
        });
    }
/**
 * Проверка на совпадения пароля
 * */
    @Test
    void passwordEncoder_ShouldUseNoOpPasswordEncoder() {

        String rawPassword = "admin";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        assertEquals(rawPassword, encodedPassword);

        assertTrue(passwordEncoder.matches(rawPassword, encodedPassword));
    }
}


