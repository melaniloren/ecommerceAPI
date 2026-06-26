package com.uade.tpo.e_commerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uade.tpo.e_commerce.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    //findAll() ya está implementado por JpaRepository, no es necesario definirlo aquí
    // select * from pedidos
    List<Pedido> findByUsuario_IdUsuario(Long idUsuario);
}
