package com.uade.tpo.e_commerce.dto;

import lombok.Data;

@Data
public class RecetaNuevaDTO {
    private String nombre;
    private String descripcion;
    private Double precio;

    public RecetaNuevaDTO(String nombre, String descripcion, Double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

}
