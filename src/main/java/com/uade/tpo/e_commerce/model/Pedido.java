package com.uade.tpo.e_commerce.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@Entity
@Table(name = "pedidos")
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Pedido;

    private LocalDate fecha;

    private Double total;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<DetallePedido> detalles;

    
}