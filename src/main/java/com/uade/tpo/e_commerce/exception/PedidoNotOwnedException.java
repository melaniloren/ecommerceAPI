package com.uade.tpo.e_commerce.exception;

public class PedidoNotOwnedException extends RecursoNotFoundException {
    public PedidoNotOwnedException(String TipoRecurso,Long id) {
        super(TipoRecurso,id);
    }
}
