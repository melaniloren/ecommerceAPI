package com.uade.tpo.e_commerce.exception;

public class RecetaDetalleNotFoundException extends RecursoNotFoundException {
    public RecetaDetalleNotFoundException(Long id) {
        super("receta detalle", id);
    }

}
