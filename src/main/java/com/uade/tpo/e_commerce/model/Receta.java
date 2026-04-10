package com.uade.tpo.e_commerce.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@Builder
@Table(name = "recetas")
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Receta;

    private String nombre;

    private String descripcion;

    private Double precioReceta;

    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL)
    private List<RecetaDetalle> recetaDetalles;

}
