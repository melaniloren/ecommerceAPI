package com.uade.tpo.e_commerce.exception;

public class UsuarioNotFoundException extends RecursoNotFoundException {
    public UsuarioNotFoundException(Long id) {
        super("No se encontró el usuario con el id: " + id);
    }

}
