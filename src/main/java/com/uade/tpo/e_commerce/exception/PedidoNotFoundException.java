package com.uade.tpo.e_commerce.exception;

public class PedidoNotFoundException extends RecursoNotFoundException {
    public PedidoNotFoundException(String TipoRecurso,Long id) {
        super(TipoRecurso,id);
    }
}
