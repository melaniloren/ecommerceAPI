package com.uade.tpo.e_commerce.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class EmailNotFoundException extends UsernameNotFoundException {
    public EmailNotFoundException(String email) {
        super("User no encontrado con email: " + email);
    }
}
