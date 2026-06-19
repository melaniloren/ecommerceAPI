package com.uade.tpo.e_commerce.service;

import java.util.List;
import java.util.stream.Collectors;

import com.uade.tpo.e_commerce.dto.RecetaDetalleRequestDTO;
import com.uade.tpo.e_commerce.model.Ingrediente;
import com.uade.tpo.e_commerce.model.Receta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.e_commerce.dto.RecetaDetalleDTO;
import com.uade.tpo.e_commerce.exception.RecetaNotFoundException;
import com.uade.tpo.e_commerce.exception.RecetaDetalleNotFoundException;
import com.uade.tpo.e_commerce.exception.IngredienteNotFoundException;
import com.uade.tpo.e_commerce.model.RecetaDetalle;
import com.uade.tpo.e_commerce.repository.RecetaRepository;
import com.uade.tpo.e_commerce.repository.RecetaDetalleRepository;
import com.uade.tpo.e_commerce.repository.IngredienteRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RecetaDetalleService {

    @Autowired
    private RecetaRepository recetaRepository;

    @Autowired
    private RecetaDetalleRepository recetaDetalleRepository;

    @Autowired
    private IngredienteRepository ingredienteRepository;

    public List<RecetaDetalleDTO> getAllRecetaDetalles() {
        return recetaDetalleRepository.findAll()
                .stream()
                .map(r -> new RecetaDetalleDTO(
                        r.getIdRecetaDetalle(),
                        r.getReceta().getIdReceta(),
                        r.getIngrediente().getIdIngrediente(),
                        r.getCantidad(),
                        r.getUnidad()))
                .collect(Collectors.toList());
    }

    public List<RecetaDetalleDTO> getRecetaDetallesByReceta(Long idReceta) {
        return recetaDetalleRepository.findByReceta_IdReceta(idReceta)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<RecetaDetalleDTO> replaceRecetaDetallesByReceta(Long idReceta, List<RecetaDetalleRequestDTO> detalles) {
        if (!recetaRepository.existsById(idReceta)) {
            throw new RecetaNotFoundException(idReceta);
        }

        List<RecetaDetalle> actuales = recetaDetalleRepository.findByReceta_IdReceta(idReceta);
        recetaDetalleRepository.deleteAll(actuales);

        return detalles.stream()
                .map(detalle -> {
                    detalle.setIdReceta(idReceta);
                    return saveRecetaDetalle(detalle);
                })
                .collect(Collectors.toList());
    }

    public RecetaDetalleDTO getRecetaDetalleById(Long id) {
        RecetaDetalle recetaDetalle = recetaDetalleRepository.findById(id).orElse(null);

        if (recetaDetalle == null) {
            throw new RecetaDetalleNotFoundException(id);
        }

        return new RecetaDetalleDTO(
                recetaDetalle.getIdRecetaDetalle(),
                recetaDetalle.getReceta().getIdReceta(),
                recetaDetalle.getIngrediente().getIdIngrediente(),
                recetaDetalle.getCantidad(),
                recetaDetalle.getUnidad());
    }

    public RecetaDetalleDTO saveRecetaDetalle(RecetaDetalleRequestDTO dto) {
        if (dto.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }
        if (dto.getIdReceta() == null) {
            throw new IllegalArgumentException("El id de la receta no puede ser nulo");
        }
        if (dto.getIdIngrediente() == null) {
            throw new IllegalArgumentException("El id del ingrediente no puede ser nulo");
        }

        Receta receta = recetaRepository.findById(dto.getIdReceta()).orElse(null);
        if (receta == null) {
            throw new RecetaNotFoundException(dto.getIdReceta());
        }

        Ingrediente ingrediente = ingredienteRepository.findById(dto.getIdIngrediente()).orElse(null);
        if (ingrediente == null) {
            throw new IngredienteNotFoundException(dto.getIdIngrediente());
        }

        RecetaDetalle recetaDetalle = RecetaDetalle.builder()
                .receta(receta)
                .ingrediente(ingrediente)
                .cantidad(dto.getCantidad())
                .unidad(dto.getUnidad())
                .build();

        RecetaDetalle guardado = recetaDetalleRepository.save(recetaDetalle);

        return new RecetaDetalleDTO(
                guardado.getIdRecetaDetalle(),
                guardado.getReceta().getIdReceta(),
                guardado.getIngrediente().getIdIngrediente(),
                guardado.getCantidad(),
                guardado.getUnidad());
    }

    public RecetaDetalleDTO updateRecetaDetalle(Long id, RecetaDetalleRequestDTO dto) {
        RecetaDetalle recetaDetalle = recetaDetalleRepository.findById(id).orElse(null);

        if (recetaDetalle == null) {
            throw new RecetaDetalleNotFoundException(id);
        }

        Receta receta = recetaRepository.findById(dto.getIdReceta()).orElse(null);
        if (receta == null) {
            throw new RecetaNotFoundException(dto.getIdReceta());
        }

        Ingrediente ingrediente = ingredienteRepository.findById(dto.getIdIngrediente()).orElse(null);
        if (ingrediente == null) {
            throw new IngredienteNotFoundException(dto.getIdIngrediente());
        }

        if (dto.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }

        recetaDetalle.setReceta(receta);
        recetaDetalle.setIngrediente(ingrediente);
        recetaDetalle.setCantidad(dto.getCantidad());
        recetaDetalle.setUnidad(dto.getUnidad());

        RecetaDetalle actualizado = recetaDetalleRepository.save(recetaDetalle);

        return new RecetaDetalleDTO(
                actualizado.getIdRecetaDetalle(),
                actualizado.getReceta().getIdReceta(),
                actualizado.getIngrediente().getIdIngrediente(),
                actualizado.getCantidad(),
                actualizado.getUnidad());
    }

    public void deleteRecetaDetalleById(Long id) {
        RecetaDetalle recetaDetalle = recetaDetalleRepository.findById(id).orElse(null);

        if (recetaDetalle == null) {
            throw new RecetaDetalleNotFoundException(id);
        }

        recetaDetalleRepository.deleteById(id);
    }

    public RecetaDetalleDTO toDTO(RecetaDetalle recetaDetalle) {
        return new RecetaDetalleDTO(
                recetaDetalle.getIdRecetaDetalle(),
                recetaDetalle.getReceta().getIdReceta(),
                recetaDetalle.getIngrediente().getIdIngrediente(),
                recetaDetalle.getCantidad(),
                recetaDetalle.getUnidad());
    }
}
