package com.uade.tpo.e_commerce.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecetaDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private List<CategoriaDTO> categorias;

    /*public RecetaDTO(Long id, String nombre, String descripcion, Double precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categorias = new ArrayList<>();
    }*/

}
