package com.uade.tpo.e_commerce.exception;

public class PedidoNotFoundEception extends RecursoNotFoundException {
    public PedidoNotFoundEception(String TipoRecurso,Long id) {
        super(TipoRecurso,id);
    }
}
