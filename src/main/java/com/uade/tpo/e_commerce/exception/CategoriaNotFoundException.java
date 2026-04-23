package com.uade.tpo.e_commerce.exception;



public class CategoriaNotFoundException extends RecursoNotFoundException {
    public CategoriaNotFoundException(String TipoRecurso,Long id) {
        super(TipoRecurso,id);
    }

}   