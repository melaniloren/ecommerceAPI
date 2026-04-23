package com.uade.tpo.e_commerce.exception;

public class IngredienteNotFoundException extends RecursoNotFoundException {
    public IngredienteNotFoundException(String TipoRecurso,Long id) {
        super(TipoRecurso,id);
    }
    
}
