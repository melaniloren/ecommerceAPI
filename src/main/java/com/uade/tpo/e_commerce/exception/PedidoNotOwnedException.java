package com.uade.tpo.e_commerce.exception;

public class PedidoNotOwnedException extends RecursoNotFoundException {
    public PedidoNotOwnedException(String message) {
        super(message);
    }
}
