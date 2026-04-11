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
    private Long id;

    @Column(nullable = false)
    private String nombre;


    @ManyToMany(mappedBy = "categorias")
    private List<Receta> recetas;
    //categoria.getProductos(); // trae los productos de la categoria
}

