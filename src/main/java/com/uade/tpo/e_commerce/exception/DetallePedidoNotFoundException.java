package com.uade.tpo.e_commerce.exception;

public class DetallePedidoNotFoundException extends RecursoNotFoundException {
    public DetallePedidoNotFoundException(String TipoRecurso,Long id) {
        super(TipoRecurso,id);
    }
    
}
