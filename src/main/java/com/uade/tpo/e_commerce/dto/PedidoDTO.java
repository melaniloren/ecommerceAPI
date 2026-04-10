package com.uade.tpo.e_commerce.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PedidoDTO {
    private Long id;
    private LocalDate fecha;
    private Double total;
    

    public PedidoDTO(Long id, LocalDate fecha, Double total) {
        this.id = id;
        this.fecha = fecha;
        this.total = total;
    }
}
     
