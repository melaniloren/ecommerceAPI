package com.uade.tpo.e_commerce.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Builder
@Data
@Table(name = "detalle_pedidos")
@AllArgsConstructor
@NoArgsConstructor
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_DetallePedido;

    private Integer cantidad;

    private Double precioTotal;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "ingrediente_id")
    private Ingrediente ingrediente;

}

  
