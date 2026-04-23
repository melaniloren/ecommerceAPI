package com.uade.tpo.e_commerce.exception;

public class RecursoNotFoundException extends RuntimeException {
    public RecursoNotFoundException(String TipoRecursoNoEncontrado, Long id) {
        super("No se pudo encontar" + TipoRecursoNoEncontrado + " con el ID: " + id);
    }
    
    public RecursoNotFoundException(String TipoRecursoNoEncontrado, String identificador) {
        super("No se pudo encontrar " + TipoRecursoNoEncontrado + " con el valor: " + identificador);
    }
}
