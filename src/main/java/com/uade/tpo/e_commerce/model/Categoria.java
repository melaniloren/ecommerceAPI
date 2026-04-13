package com.uade.tpo.e_commerce.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
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
    //categoria.getProductos(); // trae los productos de la categoria
}

