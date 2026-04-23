package com.uade.tpo.e_commerce.exception;



public class EmailNotFoundException extends RecursoNotFoundException {
    public EmailNotFoundException(String TipoRecurso,String id) {
        super(TipoRecurso,id);
    }
}
