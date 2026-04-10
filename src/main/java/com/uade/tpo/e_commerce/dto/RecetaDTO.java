package com.uade.tpo.e_commerce.dto;

import lombok.Data;

@Data
public class RecetaDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;

    public RecetaDTO(Long id, String nombre, String descripcion, Double precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

}
