package com.uade.tpo.e_commerce.dto;

import lombok.Data;

// Muestra toda la información del ingrediente: id, nombre, descripcion, stock
@Data
public class IngredienteDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Integer stock;

    // Muestra toda la información del ingrediente.
    // Se usa cuando se muestra la lista completa de ingredientes (getAllIngredientes).
    public IngredienteDTO(Long id, String nombre, String descripcion, Integer stock) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stock = stock;
    }

    // Se usa cuando se crea un ingrediente.
    // TODO: decidir si al crear un ingrediente le pasamos el stock y descartamos este constructor.
    public IngredienteDTO(Long id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stock = 0;
    }

}
