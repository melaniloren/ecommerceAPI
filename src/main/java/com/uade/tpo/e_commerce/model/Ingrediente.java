package com.uade.tpo.e_commerce.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@Entity
@Table(name = "ingredientes")
@NoArgsConstructor
@AllArgsConstructor
public class Ingrediente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ingrediente", nullable = false)
    private Long idIngrediente;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    @Column
    private Double precio;

    @Column
    private Integer stock;

    @OneToMany(mappedBy = "ingrediente", cascade = CascadeType.ALL)
    private List<RecetaDetalle> recetaDetalles;

    
}
