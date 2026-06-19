package com.uade.tpo.e_commerce.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecetaDetalleRequestDTO {
    private int cantidad;
    private String unidad; //kg, litros, etc
    @JsonAlias("recetaId")
    private Long idReceta;
    @JsonAlias("ingredienteId")
    private Long idIngrediente;

    public RecetaDetalleRequestDTO(Long idReceta, Long idIngrediente, int cantidad, String unidad) {
        this.idReceta = idReceta;
        this.idIngrediente = idIngrediente;
        this.cantidad = cantidad;
        this.unidad = unidad;
    }

}
