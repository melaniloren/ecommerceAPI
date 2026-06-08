package com.uade.tpo.e_commerce.exception;

public class PedidoNotFoundException extends RecursoNotFoundException {
    public PedidoNotFoundException(Long id) {
        super("pedido", id);
    }
}
