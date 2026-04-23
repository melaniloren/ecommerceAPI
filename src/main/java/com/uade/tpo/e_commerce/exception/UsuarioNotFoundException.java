package com.uade.tpo.e_commerce.exception;

public class UsuarioNotFoundException extends RecursoNotFoundException {
    public UsuarioNotFoundException(String TipoRecurso,Long id) {
        super(TipoRecurso,id);
    }

}
