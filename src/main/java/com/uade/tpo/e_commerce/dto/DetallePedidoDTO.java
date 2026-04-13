package com.uade.tpo.e_commerce.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data

public class DetallePedidoDTO {
    private Long id_DetallePedido;
    private Integer cantidad;
    private Double precioTotal;
    

    public DetallePedidoDTO(Long id, Integer cantidad, Double precioTotal) {
    this.id_DetallePedido = id;
    this.cantidad = cantidad;
    this.precioTotal = precioTotal;
}

}
