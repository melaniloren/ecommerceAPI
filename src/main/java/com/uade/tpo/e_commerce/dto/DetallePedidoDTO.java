package com.uade.tpo.e_commerce.dto;
import lombok.Data;

@Data
public class DetallePedidoDTO {
    private Long id_DetallePedido;
    private int cantidad;
    private double precioTotal;
    

    public DetallePedidoDTO(Long id, int cantidad, double precioTotal) {
        this.id_DetallePedido = id;
        this.cantidad = cantidad;
        this.precioTotal = precioTotal;
    }

}
