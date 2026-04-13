package com.uade.tpo.e_commerce.service;

import java.util.List;
import java.util.stream.Collectors;

import com.uade.tpo.e_commerce.dto.IngredienteSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import com.uade.tpo.e_commerce.dto.IngredienteDTO;
import com.uade.tpo.e_commerce.model.Ingrediente;
import com.uade.tpo.e_commerce.repository.IngredienteRepository;


@Service
@Transactional
public class IngredienteService {
 
    @Autowired
    private IngredienteRepository ingredienteRepository;
    
    public List<IngredienteDTO> getAllIngredientes() {
        return ingredienteRepository.findAll()
                .stream()
                .map(i -> new IngredienteDTO(
                        i.getIdIngrediente(),
                        i.getNombre(),
                        i.getDescripcion(),
                        i.getStock()
                ))
                .collect(Collectors.toList());
    }

    public IngredienteDTO getIngredienteById(Long id) {
        Ingrediente ingrediente = ingredienteRepository.findById(id).orElse(null);

        if (ingrediente == null) {
            throw new RuntimeException("Ingrediente no encontrado con id: " + id);
            // TODO: hacer las excepciones mas generalizadas
        }

        return new IngredienteDTO(
                ingrediente.getIdIngrediente(),
                ingrediente.getNombre(),
                ingrediente.getDescripcion(),
                ingrediente.getStock()
        );
    }

    public void deleteIngredienteById(Long id) {
        if (!ingredienteRepository.existsById(id)) {
            throw new RuntimeException("Ingrediente no encontrado con id: " + id);
        }
        ingredienteRepository.deleteById(id);
    }

    public IngredienteDTO createIngrediente(IngredienteSaveDTO dto) {

        Ingrediente ingrediente = Ingrediente.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .build();

        Ingrediente ingredienteGuardado = ingredienteRepository.save(ingrediente);

        return new IngredienteDTO(
                ingredienteGuardado.getIdIngrediente(),
                ingredienteGuardado.getNombre(),
                ingredienteGuardado.getDescripcion()
        );
    }

    public IngredienteDTO updateIngrediente(Long id, IngredienteSaveDTO dto) {
        Ingrediente ingrediente = ingredienteRepository.findById(id).orElse(null);

        if (ingrediente == null) {
           throw new RuntimeException("Ingrediente no encontrado con id: " + id);
           // TODO: mejorar excepciones
        }

        ingrediente.setNombre(dto.getNombre());
        ingrediente.setDescripcion(dto.getDescripcion());

        Ingrediente actualizado = ingredienteRepository.save(ingrediente);

        return new IngredienteDTO(
                actualizado.getIdIngrediente(),
                actualizado.getNombre(),
                actualizado.getDescripcion(),
                actualizado.getStock()
        );
    }

    public IngredienteDTO updateStock(Long id, Integer stock) {
        Ingrediente ingrediente = ingredienteRepository.findById(id).orElse(null);
        if (ingrediente == null) {
            throw new RuntimeException("Ingrediente no encontrado con id: " + id);
        }
        ingrediente.setStock(stock);
        Ingrediente actualizado = ingredienteRepository.save(ingrediente);
        return new IngredienteDTO(
                actualizado.getIdIngrediente(),
                actualizado.getNombre(),
                actualizado.getDescripcion(),
                actualizado.getStock()
        );
    }
}