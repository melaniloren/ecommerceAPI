package com.uade.tpo.e_commerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.e_commerce.model.DetallePedido;
import com.uade.tpo.e_commerce.repository.DetallePedidoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DetallePedidoService {
 
    @Autowired
    private DetallePedidoRepository detallePedidoRepository;
    
    public List<DetallePedido> getAllDetallesPedidos() {
        return detallePedidoRepository.findAll();
    }

    public void deleteDetallePedidoById(Long id) {
    detallePedidoRepository.deleteById(id);
    }

    public DetallePedido getDetallePedidoById(Long id) {
        return detallePedidoRepository.findById(id).orElse(null);
    }

    public DetallePedido saveDetallePedido(DetallePedido detallePedido) {
    return detallePedidoRepository.save(detallePedido);
    }


}