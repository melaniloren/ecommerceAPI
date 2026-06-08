package com.uade.tpo.e_commerce.exception;

public class UsuarioNotFoundException extends RecursoNotFoundException {
    public UsuarioNotFoundException(Long id) {
        super("usuario", id);
    }

    public UsuarioNotFoundException(String email) {
        super("usuario", email);
    }
}
