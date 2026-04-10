package com.uade.tpo.e_commerce.dto;

import lombok.Data;

@Data
public class IngredienteDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    

    public IngredienteDTO(Long id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

}
