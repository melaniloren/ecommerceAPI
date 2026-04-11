package com.uade.tpo.e_commerce.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@Table(name = "receta_detalles")
@AllArgsConstructor
@NoArgsConstructor
public class RecetaDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_RecetaDetalle;

    private Integer cantidad;

    private String unidad;

    //esta relacion es muchos a muchos pq es la intermediaria entre receta y ingrediente
    @ManyToOne
    @JoinColumn(name = "receta_id")
    private Receta receta;

    @ManyToOne
    @JoinColumn(name = "ingrediente_id")
    private Ingrediente ingrediente;

}
