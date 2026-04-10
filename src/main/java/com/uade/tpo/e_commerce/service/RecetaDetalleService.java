package com.uade.tpo.e_commerce.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.e_commerce.dto.RecetaDetalleDTO;
import com.uade.tpo.e_commerce.exception.RecetaNotFoundException;
import com.uade.tpo.e_commerce.model.RecetaDetalle;
import com.uade.tpo.e_commerce.repository.RecetaDetalleRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RecetaDetalleService {

    @Autowired
    private RecetaDetalleRepository recetaDetalleRepository;

    public List<RecetaDetalleDTO> getAllRecetaDetalles() {
        return recetaDetalleRepository.findAll()
                .stream()
                .map(r -> new RecetaDetalleDTO(
                        r.getId_RecetaDetalle(),
                        r.getCantidad(),
                        r.getUnidad()
                ))
                .collect(Collectors.toList());
    }

    public RecetaDetalleDTO getRecetaDetalleById(Long id) {
        RecetaDetalle recetaDetalle = recetaDetalleRepository.findById(id).orElse(null);

        if (recetaDetalle == null) {
            throw new RecetaNotFoundException("RecetaDetalle no encontrado con id: " + id);
        }

        return new RecetaDetalleDTO(
                recetaDetalle.getId_RecetaDetalle(),
                recetaDetalle.getCantidad(),
                recetaDetalle.getUnidad()
        );
    }

    public RecetaDetalleDTO saveRecetaDetalle(RecetaDetalleDTO dto) {

        // Validación simple
        if (dto.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }

        RecetaDetalle recetaDetalle = RecetaDetalle.builder()
                .cantidad(dto.getCantidad())
                .unidad(dto.getUnidad())
                .build();

        RecetaDetalle guardado = recetaDetalleRepository.save(recetaDetalle);

        return new RecetaDetalleDTO(
                guardado.getId_RecetaDetalle(),
                guardado.getCantidad(),
                guardado.getUnidad()
        );
    }

    public RecetaDetalleDTO updateRecetaDetalle(Long id, RecetaDetalleDTO dto) {
        RecetaDetalle recetaDetalle = recetaDetalleRepository.findById(id).orElse(null);

        if (recetaDetalle == null) {
            throw new RecetaNotFoundException("RecetaDetalle no encontrado con id: " + id);
        }

        if (dto.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }

        recetaDetalle.setCantidad(dto.getCantidad());
        recetaDetalle.setUnidad(dto.getUnidad());

        RecetaDetalle actualizado = recetaDetalleRepository.save(recetaDetalle);

        return new RecetaDetalleDTO(
                actualizado.getId_RecetaDetalle(),
                actualizado.getCantidad(),
                actualizado.getUnidad()
        );
    }

    public void deleteRecetaDetalleById(Long id) {
        RecetaDetalle recetaDetalle = recetaDetalleRepository.findById(id).orElse(null);

        if (recetaDetalle == null) {
            throw new RecetaNotFoundException("RecetaDetalle no encontrado con id: " + id);
        }

        recetaDetalleRepository.deleteById(id);
    }
}

