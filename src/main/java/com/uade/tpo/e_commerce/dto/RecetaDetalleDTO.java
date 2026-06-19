package com.uade.tpo.e_commerce.dto;

import lombok.Data;

@Data
public class RecetaDetalleDTO {
    private Long idRecetaDetalle;
    private int cantidad;
    private String unidad; //kg, litros, etc
    private Long idReceta;
    private Long idIngrediente;

    public RecetaDetalleDTO(Long id, Long idReceta, Long idIngrediente, int cantidad, String unidad) {
        this.idRecetaDetalle = id;
        this.idReceta = idReceta;
        this.idIngrediente = idIngrediente;
        this.cantidad = cantidad;
        this.unidad = unidad;
    }

    public Long getRecetaId() {
        return idReceta;
    }

    public Long getIngredienteId() {
        return idIngrediente;
    }

}
