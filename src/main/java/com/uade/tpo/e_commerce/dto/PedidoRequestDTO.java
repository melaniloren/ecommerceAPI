package com.uade.tpo.e_commerce.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PedidoRequestDTO {
    private LocalDate fecha;
    private Double total;
    private Long usuarioId;

    public PedidoRequestDTO(LocalDate fecha, Double total, Long usuarioId) {
        this.fecha = fecha;
        this.total = total;
        this.usuarioId = usuarioId;
    }

}

