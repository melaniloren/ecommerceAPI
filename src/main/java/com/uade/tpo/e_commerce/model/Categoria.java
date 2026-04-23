package com.uade.tpo.e_commerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria", nullable = false)
    private Long idCategoria;

    @Column(nullable = false)
    private String nombre;


    @ManyToMany(mappedBy = "categorias")
    private List<Receta> recetas;
    //categoria.getRecetas(); // trae los productos de la categoria
}

