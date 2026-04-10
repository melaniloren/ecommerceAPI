package com.uade.tpo.e_commerce.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ingredientes")
public class Ingrediente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Ingrediente;

    @Column(nullable = false)
    private String nombre;
    
    private String descripcion;

    @OneToMany(mappedBy = "ingrediente", cascade = CascadeType.ALL)
    private List<RecetaDetalle> recetaDetalles;

    
}
