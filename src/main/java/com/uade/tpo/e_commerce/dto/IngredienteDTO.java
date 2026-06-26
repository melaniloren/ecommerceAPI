package com.uade.tpo.e_commerce.dto;
import lombok.Data;

// Muestra toda la información del ingrediente: id, nombre, descripcion, stock
@Data
public class IngredienteDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Integer stock;
    private Double precio;   

    public IngredienteDTO(Long id, String nombre, String descripcion, Integer stock, Double precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precio = precio;  
    }

    public IngredienteDTO(Long id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stock = 0;
        this.precio = 0.0;   
    }
}