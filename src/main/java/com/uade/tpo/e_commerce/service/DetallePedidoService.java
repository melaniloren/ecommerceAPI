package com.uade.tpo.e_commerce.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import com.uade.tpo.e_commerce.dto.DetallePedidoDTO;
import com.uade.tpo.e_commerce.model.DetallePedido;
import com.uade.tpo.e_commerce.repository.DetallePedidoRepository;

import com.uade.tpo.e_commerce.exception.DetallePedidoNotFoundException;


@Service
@Transactional
public class DetallePedidoService {

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    public List<DetallePedidoDTO> getAllDetallesPedidos() {
        return detallePedidoRepository.findAll()
                .stream()
                .map(d -> new DetallePedidoDTO(d.getId_DetallePedido(), d.getCantidad(), d.getPrecioTotal()))
                .collect(Collectors.toList());
    }

    public DetallePedidoDTO getDetallePedidoById(Long id) {
        DetallePedido detalle = detallePedidoRepository.findById(id).orElse(null);

        if (detalle == null) {
            throw new DetallePedidoNotFoundException("detalle de pedido ", id);
        }

        return new DetallePedidoDTO(
                detalle.getId_DetallePedido(),
                detalle.getCantidad(),
                detalle.getPrecioTotal()
        );
    }

    public void deleteDetallePedidoById(Long id) {
        detallePedidoRepository.deleteById(id);
    }

    public DetallePedidoDTO saveDetallePedido(DetallePedidoDTO detallePedidoDTO) {
        DetallePedido detalle = DetallePedido.builder()
                .cantidad(detallePedidoDTO.getCantidad())
                .precioTotal(detallePedidoDTO.getPrecioTotal())
                .build();

        DetallePedido guardado = detallePedidoRepository.save(detalle);

        return new DetallePedidoDTO(
                guardado.getId_DetallePedido(),
                guardado.getCantidad(),
                guardado.getPrecioTotal()
        );
    }

    public DetallePedidoDTO updateDetallePedido(Long id, DetallePedidoDTO detallePedidoDTO) {
        DetallePedido detalle = detallePedidoRepository.findById(id).orElse(null);

        if (detalle == null) {
            throw new DetallePedidoNotFoundException("detalle de pedido ", id);
        }

        detalle.setCantidad(detallePedidoDTO.getCantidad());
        detalle.setPrecioTotal(detallePedidoDTO.getPrecioTotal());

        DetallePedido actualizado = detallePedidoRepository.save(detalle);

        return new DetallePedidoDTO(
                actualizado.getId_DetallePedido(),
                actualizado.getCantidad(),
                actualizado.getPrecioTotal()
        );
    }
}