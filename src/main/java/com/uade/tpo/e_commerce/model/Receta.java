package com.uade.tpo.e_commerce.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recetas")
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Receta;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    @Column(nullable = false)
    private Double precioReceta;

    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL)
    private List<RecetaDetalle> recetaDetalles;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "recetas_categorias",
            joinColumns = @JoinColumn(name = "receta_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private List<Categoria> categorias;
}
