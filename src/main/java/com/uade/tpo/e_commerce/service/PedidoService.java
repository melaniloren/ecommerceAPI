package com.uade.tpo.e_commerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import com.uade.tpo.e_commerce.model.Pedido;
import com.uade.tpo.e_commerce.repository.PedidoRepository;

@Service
@Transactional
public class PedidoService {
 
    @Autowired
    private PedidoRepository pedidoRepository;
    
    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    public void deletePedidoById(Long id) {
    pedidoRepository.deleteById(id);
    }

    public Pedido getPedidoById(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public Pedido savePedido(Pedido pedido) {
    return pedidoRepository.save(pedido);
    }


}
