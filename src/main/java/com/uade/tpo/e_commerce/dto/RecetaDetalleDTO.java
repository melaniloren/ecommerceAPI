package com.uade.tpo.e_commerce.dto;

import lombok.Data;

@Data
public class RecetaDetalleDTO {
    private Long id_RecetaDetalle;
    private int cantidad;
    private String unidad; //kg, litros, etc
    

    public RecetaDetalleDTO(Long id, int cantidad, String unidad) {
        this.id_RecetaDetalle = id;
        this.cantidad = cantidad;
        this.unidad = unidad;
    }

}
