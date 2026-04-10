package com.uade.tpo.e_commerce.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "receta_detalles")
public class RecetaDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_RecetaDetalle;

    private Integer cantidad;

    private String unidad;

    @ManyToOne
    @JoinColumn(name = "receta_id")
    private Receta receta;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

}
