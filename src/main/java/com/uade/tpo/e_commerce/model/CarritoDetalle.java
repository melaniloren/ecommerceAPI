package com.uade.tpo.e_commerce.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@Table(name = "carrito_detalles")
@AllArgsConstructor
@NoArgsConstructor
public class CarritoDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrito_detalle", nullable = false)
    private Long idCarritoDetalle;

    @ManyToOne
    @JoinColumn(name = "carrito_id", nullable = false)
    private Carrito carrito;

    @ManyToOne
    @JoinColumn(name = "receta_id", nullable = false)
    private Receta receta;

    @Column(nullable = false)
    private Integer cantidad;

    private Double precioTotal;
}

