package com.uade.tpo.e_commerce.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import com.uade.tpo.e_commerce.dto.PedidoDTO;
import com.uade.tpo.e_commerce.model.Pedido;
import com.uade.tpo.e_commerce.repository.PedidoRepository;

@Service
@Transactional
public class PedidoService {
 
    @Autowired
    private PedidoRepository pedidoRepository;
    
    public List<PedidoDTO> getAllPedidos() {
        return pedidoRepository.findAll()
                .stream()
                .map(pedido -> new PedidoDTO(
                        pedido.getId_Pedido(),
                        pedido.getFecha(),
                        pedido.getTotal()))
                .collect(Collectors.toList());
    }

    public PedidoDTO getPedidoById(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElse(null);

        if (pedido == null) {
           // throw new PedidoNotFoundException("Pedido no encontrado con id: " + id);
        }

        PedidoDTO pedidoDTO = new PedidoDTO(
                pedido.getId_Pedido(),
                pedido.getFecha(),
                pedido.getTotal());

        return pedidoDTO;
    }

    public void deletePedidoById(Long id) {
        pedidoRepository.deleteById(id);
    }

    public PedidoDTO savePedido(PedidoDTO pedidoDTO) {

        Pedido pedido = Pedido.builder()
                .fecha(pedidoDTO.getFecha())
                .total(pedidoDTO.getTotal())
                .build();

        Pedido pedidoAdd = pedidoRepository.save(pedido);

        PedidoDTO pedidoDTOAdd = new PedidoDTO(
                pedidoAdd.getId_Pedido(),
                pedidoAdd.getFecha(),
                pedidoAdd.getTotal());

        return pedidoDTOAdd;
    }

    public PedidoDTO updatePedido(Long id, PedidoDTO pedidoDTO) {

        Pedido pedido = pedidoRepository.findById(id).orElse(null);

        if (pedido == null) {
          //  throw new PedidoNotFoundException("Pedido no encontrado con id: " + id);
        }

        pedido.setFecha(pedidoDTO.getFecha());
        pedido.setTotal(pedidoDTO.getTotal());

        Pedido pedidoActualizado = pedidoRepository.save(pedido);

        PedidoDTO pedidoDTOActualizado = new PedidoDTO(
                pedidoActualizado.getId_Pedido(),
                pedidoActualizado.getFecha(),
                pedidoActualizado.getTotal());

        return pedidoDTOActualizado;
    }
}
