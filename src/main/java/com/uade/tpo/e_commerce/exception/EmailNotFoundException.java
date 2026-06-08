package com.uade.tpo.e_commerce.exception;



public class EmailNotFoundException extends RecursoNotFoundException {
    public EmailNotFoundException(String id) {
        super("email", id);
    }
}
