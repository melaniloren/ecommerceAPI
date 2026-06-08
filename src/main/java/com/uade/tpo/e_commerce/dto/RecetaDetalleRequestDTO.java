package com.uade.tpo.e_commerce.dto;

import lombok.Data;

@Data
public class RecetaDetalleRequestDTO {
    private int cantidad;
    private String unidad; //kg, litros, etc
    private Long idReceta;
    private Long idIngrediente;

    public RecetaDetalleRequestDTO(Long idReceta, Long idIngrediente, int cantidad, String unidad) {
        this.idReceta = idReceta;
        this.idIngrediente = idIngrediente;
        this.cantidad = cantidad;
        this.unidad = unidad;
    }

}
