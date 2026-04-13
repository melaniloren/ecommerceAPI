package com.uade.tpo.e_commerce.dto;

import lombok.Data;

@Data
public class IngredienteSaveDTO {
    private String nombre;
    private String descripcion;

    public IngredienteSaveDTO(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

}
