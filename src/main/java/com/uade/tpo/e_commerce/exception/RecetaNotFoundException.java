package com.uade.tpo.e_commerce.exception;

public class RecetaNotFoundException extends RuntimeException {
    public RecetaNotFoundException(Long id) {
        super("No se encontró la receta con el ID: " + id);
    }

}
