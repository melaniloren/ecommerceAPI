package com.uade.tpo.e_commerce.repository;

import com.uade.tpo.e_commerce.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {
    Optional<Carrito> findByUsuarioIdUsuario(Long usuarioId);
}

