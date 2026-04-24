package com.uade.tpo.e_commerce.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.uade.tpo.e_commerce.dto.PedidoDTO;
import com.uade.tpo.e_commerce.dto.PedidoRequestDTO;
import com.uade.tpo.e_commerce.exception.PedidoNotFoundException;
import com.uade.tpo.e_commerce.model.Pedido;
import com.uade.tpo.e_commerce.model.Usuario;
import com.uade.tpo.e_commerce.repository.PedidoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PedidoService {
 
    private Long getCurrentUserId() {
        return ((Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getIdUsuario();
    }

    @Autowired
    private PedidoRepository pedidoRepository;
    
    public List<PedidoDTO> getAllPedidos() {
        return pedidoRepository.findAll()
                .stream()
.map(pedido -> new PedidoDTO(
                        pedido.getIdPedido(),
                        pedido.getFecha(),
                        pedido.getTotal(),
                        pedido.getUsuario() != null ? pedido.getUsuario().getIdUsuario() : null))

                .collect(Collectors.toList());
    }

    public PedidoDTO getPedidoById(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElse(null);

        if (pedido == null) {
           throw new PedidoNotFoundException("pedido", id);
        }

        PedidoDTO pedidoDTO = new PedidoDTO(
                pedido.getIdPedido(),
                pedido.getFecha(),
                pedido.getTotal(),
                pedido.getUsuario() != null ? pedido.getUsuario().getIdUsuario() : null);


        return pedidoDTO;
    }

    public void deletePedidoById(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido not found"));
        if (!pedido.getUsuario().getIdUsuario().equals(getCurrentUserId())) {
            throw new PedidoNotFoundException("pedido", id);
        }
        pedidoRepository.delete(pedido);
    }

    public PedidoDTO savePedido(PedidoRequestDTO pedidoRequestDTO) {

        Pedido pedido = Pedido.builder()
                .fecha(pedidoRequestDTO.getFecha())
                .total(pedidoRequestDTO.getTotal())
                .build();
        pedido.setUsuario((Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        Pedido pedidoAdd = pedidoRepository.save(pedido);

        return new PedidoDTO(
                pedidoAdd.getIdPedido(),
                pedidoAdd.getFecha(),
                pedidoAdd.getTotal(),
                pedidoAdd.getUsuario() != null ? pedidoAdd.getUsuario().getIdUsuario() : null);
    }

    public PedidoDTO updatePedido(Long id, PedidoRequestDTO pedidoRequestDTO) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new PedidoNotFoundException( "pedido", id));
        if (!pedido.getUsuario().getIdUsuario().equals(getCurrentUserId())) {
            throw new PedidoNotFoundException( "pedido", id);
        }

        pedido.setFecha(pedidoRequestDTO.getFecha());
        pedido.setTotal(pedidoRequestDTO.getTotal());

        Pedido pedidoActualizado = pedidoRepository.save(pedido);

        return new PedidoDTO(
                pedidoActualizado.getIdPedido(),
                pedidoActualizado.getFecha(),
                pedidoActualizado.getTotal(),
                pedidoActualizado.getUsuario() != null ? pedidoActualizado.getUsuario().getIdUsuario() : null);
    }
}
