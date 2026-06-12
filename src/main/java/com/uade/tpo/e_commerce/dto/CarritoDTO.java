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
public class CarritoDTO {
    private Long id;
    private Long usuarioId;
    private String usuarioEmail;
    private Double precioTotal;
    private List<CarritoDetalleDTO> detalles;
}

