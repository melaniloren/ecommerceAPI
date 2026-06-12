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
public class CarritoDetalleDTO {
    private Long id;
    private Long recetaId;
    private String recetaNombre;
    private Double recetaPrecio;
    private Integer cantidad;
    private Double precioTotal;
    private List<RecetaDetalleDTO> ingredientes;
}

