package com.uade.tpo.e_commerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecetaRequestDTO {
    private String nombre;
    private String descripcion;
    private Double precio;
    private List<Long> categorias;

    public RecetaRequestDTO(String nombre, String descripcion, Double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categorias = new ArrayList<>();
    }

}
