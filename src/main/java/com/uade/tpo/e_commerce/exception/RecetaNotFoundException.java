package com.uade.tpo.e_commerce.exception;

public class RecetaNotFoundException extends RecursoNotFoundException {
    public RecetaNotFoundException(String TipoRecurso,Long id) {
        super(TipoRecurso,id);
    }

}
