package com.uade.tpo.e_commerce.repository;

import com.uade.tpo.e_commerce.model.CarritoDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarritoDetalleRepository extends JpaRepository<CarritoDetalle, Long> {
    Optional<CarritoDetalle> findByCarritoIdCarritoAndRecetaIdReceta(Long carritoId, Long recetaId);
}

