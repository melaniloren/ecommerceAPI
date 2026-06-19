package com.uade.tpo.e_commerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.uade.tpo.e_commerce.model.RecetaDetalle;

public interface RecetaDetalleRepository extends JpaRepository<RecetaDetalle, Long> {

    // Devuelve todos los detalles (ingredientes) que pertenecen a una receta dada.
    // Spring Data deriva la query a partir del nombre: navega receta -> idReceta.
    List<RecetaDetalle> findByReceta_IdReceta(Long idReceta);

}
