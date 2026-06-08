package com.uade.tpo.e_commerce.exception;

public class IngredienteNotFoundException extends RecursoNotFoundException {
    public IngredienteNotFoundException(Long id) {
        super("ingrediente", id);
    }
    
}
