package com.uade.tpo.e_commerce.exception;

public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException(String email) {
        super("No se encontró el usuario con el email: " + email);
    }

}
