package com.uade.tpo.e_commerce.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class IngredienteSaveDTO {
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
}