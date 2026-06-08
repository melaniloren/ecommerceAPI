package com.uade.tpo.e_commerce.exception;

public class DetallePedidoNotFoundException extends RecursoNotFoundException {
    public DetallePedidoNotFoundException(Long id) {
        super("detalle de pedido", id);
    }
    
}
