package com.uade.tpo.e_commerce.dto;

import lombok.Data;

@Data
public class CategoriaDTO {
    private Long idCategoria;
    private String nombre;

    public CategoriaDTO(Long idCategoria, String nombre) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
    }
}
