package com.uade.tpo.e_commerce.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.uade.tpo.e_commerce.model.Receta;
import org.springframework.data.jpa.repository.Query;

public interface RecetaRepository extends JpaRepository<Receta, Long> {

    @Query("SELECT r FROM Receta r WHERE r.nombre LIKE %:nombre%")
    List<Receta> findByNombre(String nombre);
}
