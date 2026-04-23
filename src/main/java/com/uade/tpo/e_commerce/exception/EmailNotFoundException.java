package com.uade.tpo.e_commerce.exception;



public class EmailNotFoundException extends RecursoNotFoundException {
    public EmailNotFoundException(String email) {
        super("User no encontrado con email: " + email);
    }
}
