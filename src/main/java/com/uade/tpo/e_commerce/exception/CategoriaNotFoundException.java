package com.uade.tpo.e_commerce.exception;



public class CategoriaNotFoundException extends RecursoNotFoundException {
    public CategoriaNotFoundException(Long id) {
        super("categoría",id);
    }

}   