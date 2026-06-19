package com.uade.tpo.e_commerce.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RecetaRequestDTO {
    private String nombre;
    private String descripcion;
    private String imagen;
    private Double precio;
    private List<Long> categorias;

    public RecetaRequestDTO(String nombre, String descripcion, String imagen, Double precio, List<Long> categorias) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.precio = precio;
        this.categorias = List.copyOf(categorias);
    }

}
